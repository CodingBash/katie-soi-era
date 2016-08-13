package edu.ilstu.business.era.repositories;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.ilstu.business.era.database.DatabaseQuery;
import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.mappers.EventMapper;
import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.transferobjects.AnnouncementTO;
import edu.ilstu.business.era.transferobjects.ClassListTO;
import edu.ilstu.business.era.transferobjects.ClassSearchTO;
import edu.ilstu.business.era.transferobjects.EventDatabaseTO;
import edu.ilstu.business.era.utilities.KatieAbstractRepository;

/**
 * {@link EventRepository} implementation
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@Component
public class EventRepositoryImpl extends KatieAbstractRepository implements EventRepository
{

	@Autowired
	private EventMapper eventMapper;

	@Autowired
	private DatabaseQuery dbQuery;

	private static final String SEARCH_CLASS_LIST = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/search";
	private static final String GET_ALL_CLASS_ANNOUNCEMENTS = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/{refId}/announcement";
	private static final String GET_CLASS_ANNOUNCEMENT = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/{refId}/announcement/{announcementId}";

	/**
	 * Retrieve all events contained in the application
	 */
	public List<Event> retrieveEventList() throws KatieResourceNotFoundException
	{
		// Get REST Template to perform operations
		RestTemplate restTemplate = new RestTemplate();

		/*
		 * Execute request to get list of all classes
		 */
		ResponseEntity<String> jsonStringResponseClassToList = restTemplate.exchange(SEARCH_CLASS_LIST, HttpMethod.GET,
				new HttpEntity<Object>(createHeaders()), new ParameterizedTypeReference<String>()
				{
				});

		/*
		 * Convert JSON response to TO
		 */
		String jsonStringClassSearchTo = jsonStringResponseClassToList.getBody();
		Type classListType = new TypeToken<ClassSearchTO>()
		{
		}.getType();
		ClassSearchTO yourClassList = new Gson().fromJson(jsonStringClassSearchTo, classListType);

		/*
		 * Get AnnouncementTO from all ClassListTO in each ClassSearchTO
		 */
		Map<String, List<AnnouncementTO>> announcemeentToClassIdMap = new HashMap<String, List<AnnouncementTO>>();
		for (ClassListTO classTo : yourClassList.getClassList())
		{

			String sectionRefId = classTo.getSectionRefId();
			if (StringUtils.isNotBlank(sectionRefId) && sectionRefId.toLowerCase() != "null")
			{

				/*
				 * Prepare and execute request
				 */
				Map<String, String> urlVariablesMap = new HashMap<String, String>();
				urlVariablesMap.put("refId", sectionRefId);
				ResponseEntity<String> jsonStringResponseAnnouncementToList = restTemplate.exchange(
						GET_ALL_CLASS_ANNOUNCEMENTS, HttpMethod.GET, new HttpEntity<Object>(createHeaders()),
						new ParameterizedTypeReference<String>()
						{
						}, urlVariablesMap);

				/*
				 * Convert JSON response to a TO
				 */
				String jsonStringAnnouncementTo = jsonStringResponseAnnouncementToList.getBody();
				Type announcementListType = new TypeToken<List<AnnouncementTO>>()
				{
				}.getType();
				List<AnnouncementTO> yourAnnouncementList = new Gson().fromJson(jsonStringAnnouncementTo,
						announcementListType);

				// Add result to map
				announcemeentToClassIdMap.put(sectionRefId, yourAnnouncementList);
			}
		}

		// Map TO group to a event list
		List<Event> eventList = eventMapper.mapEventListFromAnnouncementTOGroup(announcemeentToClassIdMap);

		return eventList;
	}

	/**
	 * Retrieve details for a single event
	 * 
	 * @param eventId
	 *            event ID
	 * @param classId
	 *            class ID
	 * @throws {@link
	 *             KatieResourceNotFoundException} when event does not exist
	 */
	@Override
	public Event retrieveEventDetail(String eventId, String classId) throws KatieResourceNotFoundException
	{
		// Get REST Template to perform operations
		RestTemplate restTemplate = new RestTemplate();

		/*
		 * Put appropriate request parameters in map to then insert in URI
		 */
		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("announcementId", eventId);
		urlVariablesMap.put("refId", classId);

		// Execute request to get event detail
		ResponseEntity<String> jsonStringResponseClassToList = restTemplate.exchange(GET_CLASS_ANNOUNCEMENT,
				HttpMethod.GET, new HttpEntity<Object>(createHeaders()), new ParameterizedTypeReference<String>()
				{
				}, urlVariablesMap);

		/*
		 * Convert JSON to TO
		 */
		String jsonStringClassSearchTo = jsonStringResponseClassToList.getBody();
		Type classListType = new TypeToken<AnnouncementTO>()
		{
		}.getType();
		AnnouncementTO yourAnnouncementTO = new Gson().fromJson(jsonStringClassSearchTo, classListType);

		// Map TO to application object
		Event event = eventMapper.mapEventFromAnnouncementTO(yourAnnouncementTO, classId);

		return event;
	}

	/**
	 * Register for event
	 * 
	 * @param userId
	 *            user ID
	 * @param eventId
	 *            event ID
	 * @param datetime
	 *            starting date of event
	 * @param classId
	 *            class ID
	 * @throws {@link
	 *             KatieActionFailedException} when unable to register
	 */
	@Override
	public void registerForEvent(String userId, String eventId, String datetime, String classId)
			throws KatieActionFailedException
	{
		dbQuery.saveRSVP(userId, eventId, datetime, classId);
	}

	/**
	 * Unregister an event from a user
	 * 
	 * @param eventId
	 *            event ID
	 * @param userId
	 *            user ID
	 * @throws {@link
	 *             KatieActionFailedException} when unable to unregister
	 */
	@Override
	public void unregisterForEvent(String eventId, String userId) throws KatieActionFailedException
	{
		dbQuery.removeRSVP(userId, eventId);

	}

	/**
	 * Retrieve registered events from a user
	 * 
	 * @params userId user ID
	 * 
	 * @throws {@link
	 *             KatieResourceNotFoundException} when unable to find events
	 */
	@Override
	public List<Event> retrieveRegisteredEventList(String userId) throws KatieResourceNotFoundException
	{
		List<EventDatabaseTO> eventToList = dbQuery.getUserRSVP(userId);
		if (!CollectionUtils.isEmpty(eventToList))
		{
			List<Event> eventList = new ArrayList<Event>(eventToList.size());
			for (EventDatabaseTO eventTo : eventToList)
			{
				eventList.add(retrieveEventDetail(eventTo.getEventId(), eventTo.getClassId()));
			}
			return eventList;
		}

		return new ArrayList<Event>();

	}

}

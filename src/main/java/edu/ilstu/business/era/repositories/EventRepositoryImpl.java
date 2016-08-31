package edu.ilstu.business.era.repositories;

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

import edu.ilstu.business.era.constants.ApplicationConstants;
import edu.ilstu.business.era.database.DatabaseQuery;
import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.mappers.EventMapper;
import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.transferobjects.AnnouncementTO;
import edu.ilstu.business.era.transferobjects.ClassListTO;
import edu.ilstu.business.era.transferobjects.ClassSearchTO;
import edu.ilstu.business.era.transferobjects.EventDatabaseTO;

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

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Retrieve all events contained in the application
	 * 
	 * @returns {@link List}<{@link Event}>
	 * @throws {@link
	 *             KatieResourceNotFoundException}
	 */
	public List<Event> retrieveEventList() throws KatieResourceNotFoundException
	{
		// Execute request to get list of all classes
		ResponseEntity<String> jsonStringResponseClassToList = executeRestCall(ApplicationConstants.SEARCH_CLASS_LIST,
				null);

		// Convert JSON response to TO
		ClassSearchTO yourClassList = convertResponseToTransferObject(jsonStringResponseClassToList,
				new TypeToken<ClassSearchTO>()
				{
				});

		// Get AnnouncementTO from all ClassListTO in each ClassSearchTO
		Map<String, List<AnnouncementTO>> announcemeentToClassIdMap = getAnnouncementToMapFromClassSearchTo(
				yourClassList);

		// Map TO group to a event list
		List<Event> eventList = eventMapper.mapEventListFromAnnouncementTOGroup(announcemeentToClassIdMap);

		return eventList;
	}

	/**
	 * From the {@link ClassSearchTO}, get {@link Map} that contains a
	 * corresponding {@link List}<{@link AnnouncementTO}> per sectionRefId
	 * 
	 * @param classSearchTo
	 *            to retrieve info from
	 * @return {@link Map}<{@link String}, {@link List}<{@link AnnouncementTO}>>
	 */
	protected Map<String, List<AnnouncementTO>> getAnnouncementToMapFromClassSearchTo(ClassSearchTO classSearchTo)
	{
		Map<String, List<AnnouncementTO>> announcemeentToClassIdMap = new HashMap<String, List<AnnouncementTO>>();
		for (ClassListTO classListTo : classSearchTo.getClassList())
		{

			String sectionRefId = classListTo.getSectionRefId();
			if (StringUtils.isNotBlank(sectionRefId) && sectionRefId.toLowerCase() != "null")
			{
				announcemeentToClassIdMap.put(sectionRefId, retrieveAnnouncementToListFromClassListTo(sectionRefId));
			}
		}
		return announcemeentToClassIdMap;
	}

	/**
	 * Retrieve all {@link AnnouncementTO} per {@link ClassListTO}. For more
	 * explanation, each class contains a list of announcements (or events).
	 * This method retrieves the list of announcements for the specified class.
	 * 
	 * @param sectionRefId
	 *            ID of the class
	 * @return {@link List}<{@link AnnouncementTO}>
	 */
	protected List<AnnouncementTO> retrieveAnnouncementToListFromClassListTo(String sectionRefId)
	{
		/*
		 * Prepare and execute request
		 */
		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("refId", sectionRefId);

		// Execute request to get all announcementTo in ClassListTo
		ResponseEntity<String> jsonStringResponseAnnouncementToList = executeRestCall(
				ApplicationConstants.GET_ALL_CLASS_ANNOUNCEMENTS, urlVariablesMap);

		// Convert JSON response to a TO
		List<AnnouncementTO> yourAnnouncementList = convertResponseToTransferObject(
				jsonStringResponseAnnouncementToList, new TypeToken<List<AnnouncementTO>>()
				{
				});

		return yourAnnouncementList;
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
		/*
		 * Put appropriate request parameters in map to then insert in URI
		 */
		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("announcementId", eventId);
		urlVariablesMap.put("refId", classId);

		// Execute request to get event detail
		ResponseEntity<String> jsonStringResponseClassToList = executeRestCall(
				ApplicationConstants.GET_CLASS_ANNOUNCEMENT, urlVariablesMap);

		// Convert JSON to TO
		AnnouncementTO yourAnnouncementTO = convertResponseToTransferObject(jsonStringResponseClassToList,
				new TypeToken<AnnouncementTO>()
				{
				});

		// Map TO to application object
		Event event = eventMapper.mapEventFromAnnouncementTO(yourAnnouncementTO, classId);

		return event;
	}

	/**
	 * Convert the JSON body inside of the {@link ResponseEntity} to the
	 * specified type
	 * 
	 * @param response
	 *            containing the JSON body
	 * @param type
	 *            to convert the JSON body to
	 * @return the converted type
	 */
	protected <T> T convertResponseToTransferObject(ResponseEntity<String> response, TypeToken<T> type)
	{
		String jsonStringClassSearchTo = response.getBody();
		T transferObject = new Gson().fromJson(jsonStringClassSearchTo, type.getType());
		return transferObject;

	}

	/**
	 * Execute a Loudcloud REST call
	 * 
	 * @param uri
	 *            of Loudcloud REST action
	 * @param uriVariablesMap
	 *            of request parameters within the URI
	 * @return {@link ResponseEntity}<{@link String}> response
	 */
	protected ResponseEntity<String> executeRestCall(String uri, Map<String, String> uriVariablesMap)
			throws KatieResourceNotFoundException
	{
		if (StringUtils.isNotBlank(uri))
		{
			if (uriVariablesMap == null)
			{
				uriVariablesMap = new HashMap<String, String>();
			}
			try
			{
				return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(createHeaders()),
						new ParameterizedTypeReference<String>()
						{
						}, uriVariablesMap);
			} catch (Exception e)
			{
				throw new KatieResourceNotFoundException(e.getMessage());
			}
		} else
		{
			throw new KatieResourceNotFoundException("URI provided is blank");
		}
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

}

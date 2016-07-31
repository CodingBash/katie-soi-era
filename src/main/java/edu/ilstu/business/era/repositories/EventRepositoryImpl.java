package edu.ilstu.business.era.repositories;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
import edu.ilstu.business.era.utilities.KatieAbstractRepository;
import edu.ilstu.business.era.utilities.RestTemplateFactory;

@Component
public class EventRepositoryImpl extends KatieAbstractRepository implements EventRepository
{

	@Autowired
	private EventMapper eventMapper;

	@Autowired
	private RestTemplateFactory restTemplateFactory;

	@Autowired
	private DatabaseQuery dbQuery;

	private static final String SEARCH_CLASS_LIST = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/search";
	private static final String GET_ALL_CLASS_ANNOUNCEMENTS = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/{refId}/announcement";
	private static final String GET_CLASS_ANNOUNCEMENT = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/{refId}/announcement/{announcementId}";
	/*
	 * @Override public List<Event> retrieveEventList(String buCode) throws
	 * KatieResourceNotFoundException { final RestTemplate restTemplate =
	 * restTemplateFactory.getObject(); Map<String, String> urlVariablesMap =
	 * new HashMap<String, String>(); urlVariablesMap.put("buCode", buCode);
	 * ResponseEntity<List<AnnouncementTO>> restResponse =
	 * restTemplate.exchange(GET_BU_ANNOUNCEMENTS_URL, HttpMethod.GET, new
	 * HttpEntity<Object>(createHeaders()), new
	 * ParameterizedTypeReference<List<AnnouncementTO>>() { }, urlVariablesMap);
	 * List<AnnouncementTO> announcementToList = restResponse.getBody();
	 * List<Event> eventList =
	 * eventMapper.mapEventListFromAnnouncementTOList(announcementToList);
	 * return eventList; }
	 * 
	 */

	/*
	 * public List<Event> retrieveEventList2() throws
	 * KatieResourceNotFoundException { final RestTemplate restTemplate =
	 * restTemplateFactory.getObject(); ObjectMapper mapper = new
	 * ObjectMapper();
	 * 
	 * ResponseEntity<String> jsonStringResponseClassToList =
	 * restTemplate.exchange(SEARCH_CLASS_LIST, HttpMethod.GET, new
	 * HttpEntity<Object>(createHeaders()), new
	 * ParameterizedTypeReference<String>() { }); String jsonStringClassTo =
	 * jsonStringResponseClassToList.getBody();
	 * 
	 * List<ClassTO> classToList = null; try { classToList =
	 * mapper.readValue(jsonStringClassTo,
	 * TypeFactory.defaultInstance().constructCollectionType(List.class,
	 * ClassTO.class)); } catch (Exception e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); }
	 * 
	 * List<AnnouncementTO> announcementToListSum = new
	 * ArrayList<AnnouncementTO>(); for (ClassTO classTo : classToList) {
	 * Map<String, String> urlVariablesMap = new HashMap<String, String>();
	 * urlVariablesMap.put("refId", classTo.getSectionRefId());
	 * ResponseEntity<String> jsonStringResponseAnnouncementToList =
	 * restTemplate.exchange( GET_ALL_CLASS_ANNOUNCEMENTS, HttpMethod.GET, new
	 * HttpEntity<Object>(createHeaders()), new
	 * ParameterizedTypeReference<String>() { }, urlVariablesMap); String
	 * jsonStringAnnouncementTo =
	 * jsonStringResponseAnnouncementToList.getBody();
	 * 
	 * List<AnnouncementTO> announcementToList = null; try { announcementToList
	 * = mapper.readValue(jsonStringClassTo,
	 * TypeFactory.defaultInstance().constructCollectionType(List.class,
	 * AnnouncementTO.class)); } catch (Exception e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * announcementToListSum.addAll(announcementToList); } List<Event> eventList
	 * = eventMapper.mapEventListFromAnnouncementTOList(announcementToListSum);
	 * return eventList; }
	 * 
	 */

	public List<Event> retrieveEventList() throws KatieResourceNotFoundException
	{

		/*
		 * Get SEARCH_CLASS_LIST response
		 */
		final RestTemplate restTemplate = restTemplateFactory.getObject();
		ResponseEntity<String> jsonStringResponseClassToList = restTemplate.exchange(SEARCH_CLASS_LIST, HttpMethod.GET,
				new HttpEntity<Object>(createHeaders()), new ParameterizedTypeReference<String>()
				{
				});
		String jsonStringClassSearchTo = jsonStringResponseClassToList.getBody();
		Type classListType = new TypeToken<ClassSearchTO>()
		{
		}.getType();
		ClassSearchTO yourClassList = new Gson().fromJson(jsonStringClassSearchTo, classListType);

		/*
		 * Get AnnouncementTO from all ClassListTO in each ClassSearchTO
		 */
<<<<<<< HEAD
		//List<AnnouncementTO> announcementToListSum = new ArrayList<AnnouncementTO>();
		Map<String, List<AnnouncementTO>> announcemeentToClassIdMap = new HashMap<String, List<AnnouncementTO>>();
		for (ClassListTO classTo : yourClassList.getClassList()) {

			// Check if "sectionRefId" exists
			if (classTo.getSectionRefId() != null && classTo.getSectionRefId().toLowerCase() != "null") {
=======
		List<AnnouncementTO> announcementToListSum = new ArrayList<AnnouncementTO>();
		for (ClassListTO classTo : yourClassList.getClassList())
		{

			// Check if "sectionRefId" exists
			if (classTo.getSectionRefId() != null && classTo.getSectionRefId().toLowerCase() != "null")
			{

>>>>>>> local-database
				/*
				 * Get GET_ALL_CLASS_ANNOUNCEMENTS response
				 */
				String refId = classTo.getSectionRefId();
				
				Map<String, String> urlVariablesMap = new HashMap<String, String>();
				urlVariablesMap.put("refId", refId);
				ResponseEntity<String> jsonStringResponseAnnouncementToList = restTemplate.exchange(
						GET_ALL_CLASS_ANNOUNCEMENTS, HttpMethod.GET, new HttpEntity<Object>(createHeaders()),
						new ParameterizedTypeReference<String>()
						{
						}, urlVariablesMap);
				String jsonStringAnnouncementTo = jsonStringResponseAnnouncementToList.getBody();

				Type announcementListType = new TypeToken<List<AnnouncementTO>>()
				{
				}.getType();
				List<AnnouncementTO> yourAnnouncementList = new Gson().fromJson(jsonStringAnnouncementTo,
						announcementListType);
				
				announcemeentToClassIdMap.put(refId, yourAnnouncementList);
				//announcementToListSum.addAll(yourAnnouncementList);
			}
		}
		List<Event> eventList = eventMapper.mapEventListFromAnnouncementTOGroup(announcemeentToClassIdMap);
		return eventList;
	}

	@Override
	public Event retrieveEventDetail(String eventId, String classId) throws KatieResourceNotFoundException
	{

		/*
		 * Get SEARCH_CLASS_LIST response
		 */
		final RestTemplate restTemplate = restTemplateFactory.getObject();

		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("refId", classId);
		urlVariablesMap.put("announcementId", eventId);

		ResponseEntity<String> jsonStringResponseClassToList = restTemplate.exchange(GET_CLASS_ANNOUNCEMENT,
				HttpMethod.GET, new HttpEntity<Object>(createHeaders()), new ParameterizedTypeReference<String>()
				{
				}, urlVariablesMap);

		String jsonStringClassSearchTo = jsonStringResponseClassToList.getBody();
		Type classListType = new TypeToken<AnnouncementTO>()
		{
		}.getType();
		AnnouncementTO yourAnnouncementTO = new Gson().fromJson(jsonStringClassSearchTo, classListType);
		Event event = eventMapper.mapEventFromAnnouncementTO(yourAnnouncementTO);
		return event;
	}

	@Override
	@Transactional
	public void registerForEvent(String userId, String eventId, String datetime, String classId)
			throws KatieActionFailedException
	{
		System.out.println("in repo");
		dbQuery.saveRSVP(userId, eventId, datetime, classId);
	}

	@Override
	@Transactional
	public void unregisterForEvent(String announcementId, String refId) throws KatieActionFailedException
	{
		System.out.println("in repo");
		dbQuery.removeRSVP(refId, announcementId);

	}

}

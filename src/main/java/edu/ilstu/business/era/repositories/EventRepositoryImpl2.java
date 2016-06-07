package edu.ilstu.business.era.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.mappers.EventMapper;
import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.transferobjects.AnnouncementTO;

@Component
public class EventRepositoryImpl2 implements EventRepository {

	@Autowired
	private EventMapper eventMapper;

	@Override
	public List<Event> retrieveEventList(String buCode) throws KatieResourceNotFoundException {
		RestTemplate restTemplate = new RestTemplate();
		String getBuAnnouncementsURL = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/bu/{buCode}/announcement";
		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("buCode", buCode);
		ResponseEntity<List<AnnouncementTO>> restResponse = restTemplate.exchange(getBuAnnouncementsURL, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<AnnouncementTO>>() {
				}, urlVariablesMap);
		List<AnnouncementTO> announcementToList = restResponse.getBody();
		List<Event> eventList = eventMapper.mapEventListFromAnnouncementTOList(announcementToList);
		return eventList;
	}

	@Override
	public Event retrieveEventDetail(String buCode, String announcementId) throws KatieResourceNotFoundException {
		RestTemplate restTemplate = new RestTemplate();
		String getBuAnnouncementURL = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/bu/{buCode}/announcement/{announcementId}";
		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("buCode", buCode);
		urlVariablesMap.put("announcementId", announcementId);
		AnnouncementTO announcementTo = restTemplate.getForObject(getBuAnnouncementURL, AnnouncementTO.class,
				urlVariablesMap);
		Event event = eventMapper.mapEventFromAnnouncementTO(announcementTo);
		return event;
	}

	// TODO: Determine how to register for event on Loudcloud.
	@Deprecated
	@Override
	public void registerForEvent(String announcementId, String refId) throws KatieActionFailedException {
		// TODO Auto-generated method stub

	}

}

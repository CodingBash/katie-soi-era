package edu.ilstu.business.era.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.mappers.EventMapper;
import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.transferobjects.AnnouncementTO;
import edu.ilstu.business.era.utilities.KatieAbstractRepository;
import edu.ilstu.business.era.utilities.RestTemplateFactory;

@Component
public class EventRepositoryImpl2 extends KatieAbstractRepository implements EventRepository {

	@Autowired
	private EventMapper eventMapper;

	@Autowired
	private RestTemplateFactory restTemplateFactory;

	@Override
	public List<Event> retrieveEventList(String buCode) throws KatieResourceNotFoundException {
		final RestTemplate restTemplate = restTemplateFactory.getObject();

		String getBuAnnouncementsURL = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/bu/{buCode}/announcement";
		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("buCode", buCode);
		ResponseEntity<List<AnnouncementTO>> restResponse = restTemplate.exchange(getBuAnnouncementsURL, HttpMethod.GET,
				new HttpEntity<Object>(createHeaders()), new ParameterizedTypeReference<List<AnnouncementTO>>() {
				}, urlVariablesMap);
		List<AnnouncementTO> announcementToList = restResponse.getBody();
		List<Event> eventList = eventMapper.mapEventListFromAnnouncementTOList(announcementToList);
		return eventList;
	}

	@Override
	public Event retrieveEventDetail(String buCode, String announcementId) throws KatieResourceNotFoundException {
		final RestTemplate restTemplate = restTemplateFactory.getObject();
		String getBuAnnouncementURL = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/bu/{buCode}/announcement/{announcementId}";
		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("buCode", buCode);
		urlVariablesMap.put("announcementId", announcementId);
		ResponseEntity<AnnouncementTO> restResponse = restTemplate.exchange(getBuAnnouncementURL, HttpMethod.GET,
				new HttpEntity<Object>(createHeaders()), new ParameterizedTypeReference<AnnouncementTO>() {
				}, urlVariablesMap);
		AnnouncementTO announcementTo = restResponse.getBody();
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

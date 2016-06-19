package edu.ilstu.business.era.mappers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.transferobjects.AnnouncementTO;

/**
 * Mapping from {@link AnnouncementTO} to {@link Event}
 * 
 * @author Basheer
 *
 */
@Component
public class EventMapper {

	/**
	 * Maps {@link List}<{@link AnnouncementTO}> to {@link List}<{@link Event}>
	 * 
	 * @param announcementToList
	 * @return
	 */
	public List<Event> mapEventListFromAnnouncementTOList(List<AnnouncementTO> announcementToList) {
		List<Event> eventList = new ArrayList<Event>(announcementToList.size());

		for (AnnouncementTO announcementTo : announcementToList) {
			eventList.add(mapEventFromAnnouncementTO(announcementTo));
		}

		return eventList;
	}

	/**
	 * Maps the {@link AnnouncementTO} to the {@link Event} object
	 * 
	 * @author Basheer
	 *
	 */
	public Event mapEventFromAnnouncementTO(AnnouncementTO announcementTo) {
		Event event = new Event();

		event.setEventId(announcementTo.getClassAnnouncementId());
		event.setTitle(announcementTo.getTitle());
		event.setDescription(announcementTo.getDescription());

		// TODO: Verify that date conversion works
		event.setStartDate(new DateTime(announcementTo.getStartDate()));
		event.setEndDate(new DateTime(announcementTo.getEndDate()));

		// TODO: Complete mapping for other event fields

		return event;
	}
}

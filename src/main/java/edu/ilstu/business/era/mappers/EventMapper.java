package edu.ilstu.business.era.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.transferobjects.AnnouncementTO;

/**
 * Mapping from {@link AnnouncementTO} to {@link Event}
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@Component
public class EventMapper
{

	/**
	 * Maps {@link List}<{@link AnnouncementTO}> to {@link List}<{@link Event}>
	 * 
	 * @param announcementToList
	 * @return
	 */
	public List<Event> mapEventListFromAnnouncementTOGroup(List<AnnouncementTO> announcementToList)
	{
		List<Event> eventList = new ArrayList<Event>(announcementToList.size());

		for (AnnouncementTO announcementTo : announcementToList)
		{
			eventList.add(mapEventFromAnnouncementTO(announcementTo, null));
		}

		return eventList;
	}

	/**
	 * Maps {@link Map}<{@link String}, {@link AnnouncementTO}> to
	 * {@link List}<{@link Event}>
	 * 
	 * @param announcementToList
	 * @return
	 */
	public List<Event> mapEventListFromAnnouncementTOGroup(Map<String, List<AnnouncementTO>> announcementToMap)
	{
		List<Event> eventList = new ArrayList<Event>(announcementToMap.size());

		for (Map.Entry<String, List<AnnouncementTO>> entry : announcementToMap.entrySet())
		{
			String classRefId = entry.getKey();
			List<AnnouncementTO> announcementToList = entry.getValue();

			List<Event> partialEventList = new ArrayList<Event>(announcementToList.size());
			for (AnnouncementTO announcementTo : announcementToList)
			{
				partialEventList.add(mapEventFromAnnouncementTO(announcementTo, classRefId));
			}

			eventList.addAll(partialEventList);
		}

		return eventList;
	}

	/**
	 * Maps the {@link AnnouncementTO} to the {@link Event} object
	 * 
	 * @author Basheer
	 *
	 */
	public Event mapEventFromAnnouncementTO(AnnouncementTO announcementTo, String classRefId)
	{
		Event event = new Event();

		if (StringUtils.isNotBlank(classRefId))
		{
			event.setClassId(classRefId);
		}
		event.setEventId(announcementTo.getClassAnnouncementId());
		event.setTitle(announcementTo.getTitle());
		event.setDescription(announcementTo.getDescription());

		event.setStartDate(new DateTime(announcementTo.getStartDate()));
		event.setEndDate(new DateTime(announcementTo.getEndDate()));

		return event;
	}
}

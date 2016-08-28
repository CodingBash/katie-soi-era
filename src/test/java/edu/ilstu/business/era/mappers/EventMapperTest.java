package edu.ilstu.business.era.mappers;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.transferobjects.AnnouncementTO;

/**
 * {@link EventMapper} test
 * 
 * @see EventMapper
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EventMapperTest
{
	public static final String CLASS_ANNOUNCEMENT_ID = "classAnnouncementId";
	public static final String TITLE = "Title";
	public static final String DESCRIPTION = "Description";
	public static final String START_DATE = "28-08-16";
	public static final String END_DATE = "28-08-16";
	public static final String CLASS_REF_ID = "classRefId";

	private EventMapper eventMapper;

	/**
	 * Initialize the {@link EventMapper}
	 */
	@Before
	public void init()
	{
		eventMapper = new EventMapper();
	}

	/**
	 * @method {@link EventMapper#mapEventListFromAnnouncementTOGroup(List)}
	 * @objective Verify that mapping in method works as expected
	 * @expectedResults Mapping in method works as expected
	 */
	@Test
	public void testMapEventListFromAnnouncementTOGroupOfList()
	{
		List<Event> expectedEventList = createEventList(false);
		List<Event> actualEventList = eventMapper.mapEventListFromAnnouncementTOGroup(createAnnouncementToList());
		assertTrue(EqualsBuilder.reflectionEquals(expectedEventList.get(0), actualEventList.get(0)));
	}

	/**
	 * @method {@link EventMapper#mapEventListFromAnnouncementTOGroup(Map)}
	 * @objective Verify that mapping in method works as expected
	 * @expectedResults Mapping in method works as expected
	 */
	@Test
	public void testMapEventListFromAnnouncementTOGroupOfMap()
	{
		List<Event> expectedEventList = createEventList(true);
		List<Event> actualEventList = eventMapper.mapEventListFromAnnouncementTOGroup(createAnnouncementToMap());
		assertTrue(EqualsBuilder.reflectionEquals(expectedEventList.get(0), actualEventList.get(0)));
	}

	/**
	 * @method {@link EventMapper#mapEventFromAnnouncementTO(AnnouncementTO, String)}
	 * @objective Verify that mapping in method works as expected
	 * @expectedResults Mapping in method works as expected
	 */
	@Test
	public void testMapEventFromAnnouncementTO()
	{
		Event expectedEvent = createEvent(true);
		Event actualEvent = eventMapper.mapEventFromAnnouncementTO(createAnnouncementTo(), CLASS_REF_ID);
		assertTrue(EqualsBuilder.reflectionEquals(expectedEvent, actualEvent));
	}

	/**
	 * Creates mock {@link Event} that corresponds to {@link AnnouncementTO} in
	 * {@link EventMapperTest#createAnnouncementTo()}
	 * 
	 * @param hasClassRefId
	 *            if will contain the classRefId field
	 * @return Mocked {@link Event}
	 */
	public Event createEvent(boolean hasClassRefId)
	{
		Event event = new Event();
		event.setEventId(CLASS_ANNOUNCEMENT_ID);
		event.setTitle(TITLE);
		event.setDescription(DESCRIPTION);
		event.setStartDate(new DateTime(START_DATE));
		event.setEndDate(new DateTime(END_DATE));
		if (hasClassRefId)
			event.setClassId(CLASS_REF_ID);
		return event;
	}

	/**
	 * Creates mock {@link AnnouncementTO} that corresponds to {@link Event} in
	 * {@link EventMapperTest#createEvent(boolean)}
	 * 
	 * @return Mocked {@link AnnouncementTO}
	 */
	public AnnouncementTO createAnnouncementTo()
	{
		AnnouncementTO announcementTo = new AnnouncementTO();
		announcementTo.setClassAnnouncementId(CLASS_ANNOUNCEMENT_ID);
		announcementTo.setTitle(TITLE);
		announcementTo.setDescription(DESCRIPTION);
		announcementTo.setStartDate(START_DATE);
		announcementTo.setEndDate(END_DATE);
		return announcementTo;
	}

	/**
	 * Creates mock {@link List}<{@link Event}> that corresponds to
	 * {@link List}<{@link AnnouncementTO}> in
	 * {@link EventMapperTest#createAnnouncementToList()}
	 * 
	 * @param hasClassRefId
	 *            if containing event will contain the classRefId field
	 * @return Mocked {@link List}<{@link Event}>
	 */
	public List<Event> createEventList(boolean hasClassRefId)
	{
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(createEvent(hasClassRefId));
		return eventList;
	}

	/**
	 * Creates mock {@link List}<{@link AnnouncementTO}> that corresponds to
	 * {@link List}<{@link Event}> in
	 * {@link EventMapperTest#createEventList(boolean)}
	 * 
	 * @return Mocked {@link List}<{@link AnnouncementTO}>
	 */
	public List<AnnouncementTO> createAnnouncementToList()
	{
		List<AnnouncementTO> announcementToList = new ArrayList<AnnouncementTO>();
		announcementToList.add(createAnnouncementTo());
		return announcementToList;
	}

	/**
	 * Creates mock {@link Map}<{@link String}, {@link AnnouncementTO}> that
	 * corresponds to {@link List}<{@link Event}> in
	 * {@link EventMapperTest#createEventList(boolean)}
	 * 
	 * @return Mocked {@link Map}<{@link String}, {@link AnnouncementTO}>
	 */
	public Map<String, List<AnnouncementTO>> createAnnouncementToMap()
	{
		Map<String, List<AnnouncementTO>> announcementToMap = new HashMap<String, List<AnnouncementTO>>();
		announcementToMap.put(CLASS_REF_ID, createAnnouncementToList());
		return announcementToMap;
	}
}

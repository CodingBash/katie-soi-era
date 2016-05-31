package edu.ilstu.business.era.repositories;

import java.util.List;

import edu.ilstu.business.era.constants.PageSort;
import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.models.Event;

public interface EventRepository {

	public List<Event> retrieveEventList(Integer page, PageSort sortEnum, int count)
			throws KatieResourceNotFoundException;

	public Event retrieveEventDetail(long eventId) throws KatieResourceNotFoundException;

	public void registerForEvent(long eventId, long userId) throws KatieActionFailedException;
}

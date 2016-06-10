package edu.ilstu.business.era.repositories;

import java.util.List;

import edu.ilstu.business.era.constants.PageSort;
import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.models.Event;

public interface EventRepository {

	public List<Event> retrieveEventList(String buCode) throws KatieResourceNotFoundException;

	public Event retrieveEventDetail(String buCode, String announcementId) throws KatieResourceNotFoundException;

	public void registerForEvent(String announcementId, String refId) throws KatieActionFailedException;
}

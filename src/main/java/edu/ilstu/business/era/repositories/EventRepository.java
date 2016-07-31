package edu.ilstu.business.era.repositories;

import java.util.List;

import org.w3c.dom.events.EventException;

import edu.ilstu.business.era.constants.PageSort;
import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.models.Event;

/**
 * Repository interface for Event retrieval and registration
 * 
 * @see EventRepositoryImpl
 * 
 * @author Basheer
 *
 */
public interface EventRepository
{

	/**
	 * Retrieves a {@link List}<{@link Event}>
	 * 
	 * @see EventRepositoryImpl#retrieveEventList()
	 * @return
	 * @throws KatieResourceNotFoundException
	 */
	public List<Event> retrieveEventList() throws KatieResourceNotFoundException;

	/**
	 * Retrieves an {@link Event}
	 * 
	 * @see EventRepositoryImpl#retrieveEventDetail(String, String)
	 * @param buCode
	 * @param announcementId
	 * @return
	 * @throws KatieResourceNotFoundException
	 */
	public Event retrieveEventDetail(String buCode, String announcementId) throws KatieResourceNotFoundException;

	/**
	 * Registers a user to an {@link Event}
	 * 
	 * @see EventRepositoryImpl#registerForEvent(String, String)
	 * @param announcementId
	 * @param refId
	 * @throws KatieActionFailedException
	 */
	public void registerForEvent(String userId, String eventId, String datetime, String classId) throws KatieActionFailedException;

	/**
	 * Unregisters a user to an {@link Event}
	 * 
	 * @param announcementId
	 * @param refId
	 * @throws KatieActionFailedException
	 */
	public void unregisterForEvent(String announcementId, String refId) throws KatieActionFailedException;

}

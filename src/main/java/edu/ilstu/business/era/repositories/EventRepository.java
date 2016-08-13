package edu.ilstu.business.era.repositories;

import java.util.List;

import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.models.Event;

/**
 * Repository interface for Event retrieval and registration
 * 
 * @see EventRepositoryImpl
 * 
 * @author Basheer Becerra (ULID: bbecer2)
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
	 * @param classId
	 * @param announcementId
	 * @return
	 * @throws KatieResourceNotFoundException
	 */
	public Event retrieveEventDetail(String classId, String eventId) throws KatieResourceNotFoundException;

	/**
	 * Registers a user to an {@link Event}
	 * 
	 * @see EventRepositoryImpl#registerForEvent(String, String)
	 * @param announcementId
	 * @param refId
	 * @throws KatieActionFailedException
	 */
	public void registerForEvent(String userId, String eventId, String datetime, String classId)
			throws KatieActionFailedException;

	/**
	 * Unregisters a user to an {@link Event}
	 * 
	 * @param eventId
	 * @param userId
	 * @throws KatieActionFailedException
	 */
	public void unregisterForEvent(String eventId, String userId) throws KatieActionFailedException;

	/**
	 * Retrieves a {@link List}<{@link Event}> that the user has registered to.
	 * 
	 * @param userId
	 *            of user with registered events
	 * @return {@link List}<{@link Event}> that the user has registered to.
	 * @throws KatieResourceNotFoundException
	 */
	public List<Event> retrieveRegisteredEventList(String userId) throws KatieResourceNotFoundException;

	
}

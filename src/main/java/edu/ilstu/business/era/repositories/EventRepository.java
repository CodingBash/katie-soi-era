package edu.ilstu.business.era.repositories;

import java.util.List;

import edu.ilstu.business.era.models.Event;

public interface EventRepository {

	public List<Event> retrieveEventList(Integer page, String sort, int count);
	
	public Event retrieveEventDetail(long eventId);
		
	public boolean registerForEvent(long userId, long eventId);
}

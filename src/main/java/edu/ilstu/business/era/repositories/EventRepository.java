package edu.ilstu.business.era.repositories;

import java.util.List;

import edu.ilstu.business.era.models.Event;

public interface EventRepository {

	public List<Event> retrieveAllEvents(Integer page, String sort, int count);
	
	public Event retrieveEventDetails(long eventId);
		
}

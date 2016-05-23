package edu.ilstu.business.era.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.models.Location;
import edu.ilstu.business.era.models.User;

public class EventRepositoryImpl implements EventRepository {

	@Override
	public List<Event> retrieveAllEvents(Integer page, String sort, int count) {
		List<Event> eventList = new ArrayList<Event>(count);
		for (int i = 0; i < count; i++) {
			Event event = new Event();
			User host = new User();
			Location location = new Location();
			host.setUsername("username" + i);
			location.setName("Illinois State");

			event.setHost(host);
			event.setEventName("TestEvent" + i);
			event.setEventDescription(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi est magna, tempor eu fermentum id, dapibus a felis. Suspendisse potenti. Sed porttitor velit libero, laoreet vulputate ex blandit id. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus vel erat ut orci blandit faucibus. Nullam urna nisl, pharetra quis laoreet et, finibus nec velit. Sed ac quam sed leo pellentesque cursus sed quis erat. Nullam sed dolor ac lacus rhoncus elementum. Vivamus tempor metus ut urna vulputate imperdiet. Duis justo nisl, pellentesque at enim sed, posuere auctor lectus. Suspendisse dictum lectus nulla, ac blandit arcu luctus in. Nulla pulvinar egestas neque sed luctus.");
			event.setAdditionalInformation("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
			event.setDate(new Date());
			event.setLocation(location);
		}
		return eventList;
	}

	@Override
	public Event retrieveEventDetails(long eventId) {
		Event event = new Event();
		User host = new User();
		Location location = new Location();
		host.setUsername("username");
		location.setName("Illinois State");

		event.setHost(host);
		event.setEventName("TestEvent");
		event.setEventDescription(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi est magna, tempor eu fermentum id, dapibus a felis. Suspendisse potenti. Sed porttitor velit libero, laoreet vulputate ex blandit id. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus vel erat ut orci blandit faucibus. Nullam urna nisl, pharetra quis laoreet et, finibus nec velit. Sed ac quam sed leo pellentesque cursus sed quis erat. Nullam sed dolor ac lacus rhoncus elementum. Vivamus tempor metus ut urna vulputate imperdiet. Duis justo nisl, pellentesque at enim sed, posuere auctor lectus. Suspendisse dictum lectus nulla, ac blandit arcu luctus in. Nulla pulvinar egestas neque sed luctus.");
		event.setAdditionalInformation("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		event.setDate(new Date());
		event.setLocation(location);
		return event;
	}

}

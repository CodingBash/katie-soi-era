package edu.ilstu.business.era.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.models.Location;
import edu.ilstu.business.era.models.User;

public class EventRepositoryImpl implements EventRepository {

	// TODO: Only retrieve active events (need to adjust event model).
	@Override
	public List<Event> retrieveEventList(String buCode) throws KatieResourceNotFoundException {
		List<Event> eventList = new ArrayList<Event>(20);
		for (int i = 0; i < 20; i++) {
			Event event = new Event();
			User host = new User();
			Location location = new Location();
			host.setUsername("username" + i);
			location.setName("Illinois State");
			event.setId(12345L);
			event.setHost(host);
			event.setTitle("TestEvent" + i);
			event.setDescription(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi est magna, tempor eu fermentum id, dapibus a felis. Suspendisse potenti. Sed porttitor velit libero, laoreet vulputate ex blandit id. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus vel erat ut orci blandit faucibus. Nullam urna nisl, pharetra quis laoreet et, finibus nec velit. Sed ac quam sed leo pellentesque cursus sed quis erat. Nullam sed dolor ac lacus rhoncus elementum. Vivamus tempor metus ut urna vulputate imperdiet. Duis justo nisl, pellentesque at enim sed, posuere auctor lectus. Suspendisse dictum lectus nulla, ac blandit arcu luctus in. Nulla pulvinar egestas neque sed luctus.");
			event.setAdditionalInformation("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
			event.setPoints(new Random().nextInt(100));
			event.setDate(generateRandomDate());
			event.setLocation(location);
			eventList.add(event);
		}
		return eventList;
	}

	@Override
	public Event retrieveEventDetail(long eventId) throws KatieResourceNotFoundException {
		Event event = new Event();
		User host = new User();
		Location location = new Location();
		host.setUsername("username");
		location.setName("Illinois State");
		event.setId(12345L);
		event.setHost(host);
		event.setTitle("TestEvent");
		event.setDescription(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi est magna, tempor eu fermentum id, dapibus a felis. Suspendisse potenti. Sed porttitor velit libero, laoreet vulputate ex blandit id. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus vel erat ut orci blandit faucibus. Nullam urna nisl, pharetra quis laoreet et, finibus nec velit. Sed ac quam sed leo pellentesque cursus sed quis erat. Nullam sed dolor ac lacus rhoncus elementum. Vivamus tempor metus ut urna vulputate imperdiet. Duis justo nisl, pellentesque at enim sed, posuere auctor lectus. Suspendisse dictum lectus nulla, ac blandit arcu luctus in. Nulla pulvinar egestas neque sed luctus.");
		event.setAdditionalInformation("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		event.setPoints(1);
		event.setDate(generateRandomDate());
		event.setLocation(location);
		return event;
	}

	@Override
	public void registerForEvent(long eventId, long userId) throws KatieActionFailedException {
		System.out.println("Event ID: " + eventId);
		System.out.println("User ID: " + userId);
	}

	public DateTime generateRandomDate() {
		Random r = new Random();

		DateTime endTime = new DateTime(r.nextInt(2016 - 2000) + 2000, r.nextInt(12 - 1) + 1, r.nextInt(28 - 1) + 1,
				r.nextInt(24 - 1) + 1, r.nextInt(59 - 1) + 1);

		return endTime;
	}

}

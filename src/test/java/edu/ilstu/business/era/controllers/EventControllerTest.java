package edu.ilstu.business.era.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.repositories.EventRepository;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class EventControllerTest {

	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventController eventController;

	private MockMvc mockMvc;

	private List<Event> expectedEvents;

	@Before
	public void setup() throws Exception {
		// Init the mocks
		MockitoAnnotations.initMocks(this);

		expectedEvents = createExpectedEvents();

		Mockito.when((eventRepository.retrieveEventList())).thenReturn(expectedEvents);

		mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
	}

	/**
	 * Tests method mapping and view name
	 * 
	 * @method {@link EventsController#eventList(int, String, int)}
	 * @perform Send a mock GET "/events/"
	 * 
	 * @expectedResults Receives correct view name "events"
	 * @expectedResults HTTP OK
	 * @expectedResults Correct forward url "events"
	 * @expectedResults Has an "events" attribute
	 * @expectedResults "events" attribute is same as mock
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEventList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/events")).andExpect(MockMvcResultMatchers.view().name("eventList"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("eventList"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("eventList")).andExpect(MockMvcResultMatchers
						.model().attribute("eventList", Matchers.hasItems(expectedEvents.toArray())));
	}

	public List<Event> createExpectedEvents() {
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < 10; i++) {
			Event event = new Event();
			event.setTitle("Event: " + i);
			event.setDescription("Description");
			events.add(event);
		}
		return events;
	}
}

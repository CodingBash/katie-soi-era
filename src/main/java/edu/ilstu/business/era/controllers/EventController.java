package edu.ilstu.business.era.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.business.era.constants.ApplicationConstants;
import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.repositories.EventRepository;

/**
 * Controls event mappings
 * 
 * @author Basheer
 *
 */
@RequestMapping("/events")
@Controller
public class EventController {

	/**
	 * Repository to get event data
	 */
	@Autowired
	private EventRepository eventRepository;

	// TODO: Add validation on all fields
	/**
	 * Sets up page to display a {@link List} of {@link Event}
	 * 
	 * @param eventId
	 *            of first {@link Event}
	 * @param count
	 *            of amount of {@link Event}s
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView eventList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "sort", defaultValue = "newest") String sort,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		// Create ModelAndView and set view to "events"
		ModelAndView mav = new ModelAndView(ApplicationConstants.pageFolder + "events");

		// Insert consistent content to model
		// bindContentToModel(mav);

		// Retrieve list from repository using request parameters
		List<Event> retrievedEventList = eventRepository.retrieveEventList(page, sort, count);

		// Insert list to model
		mav.addObject("events", retrievedEventList);

		return mav;
	}

	/**
	 * Sets up page to display details of an event
	 * 
	 * @param eventId
	 *            of event
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
	public ModelAndView eventDetails(@PathVariable(value = "eventId") long eventId) {
		// Create ModelAndView and set view to "event"
		ModelAndView mav = new ModelAndView(ApplicationConstants.pageFolder + "event");

		// Insert consistent content to model
		// bindContentToModel(mav);

		// Retrieve event from repository using request parameter
		Event retrievedEvent = eventRepository.retrieveEventDetail(eventId);

		// Insert event to model
		mav.addObject("event", retrievedEvent);

		return mav;
	}

	// TODO: Correct the mapping of this
	// TODO: Need validation and CSRF
	@RequestMapping(value = "/event/{eventId}/register", method = RequestMethod.POST)
	public ModelAndView eventRegister(@PathVariable(value = "eventId") long eventId,
			@RequestParam(value = "userId") long userId) {
		// Create ModelAndView and set view to redirect link
		ModelAndView mav = new ModelAndView("redirect: /events?eventId=" + eventId);

		// Insert consistent content to model
		// bindContentToModel(mav);

		// Register user to event using request parameters
		eventRepository.registerForEvent(eventId, userId);

		return mav;
		// TODO: Add flashattribute to determine confirmation. Then have
		// notify.js confirmation
	}
}

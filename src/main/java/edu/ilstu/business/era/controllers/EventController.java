package edu.ilstu.business.era.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.business.era.constants.PageSort;
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

	/**
	 * Sets up page to display a {@link List} of {@link Event}
	 * 
	 * @param eventId
	 *            of first {@link Event}
	 * @param count
	 *            of amount of {@link Event}s
	 * @return {@link ModelAndView}
	 */
	@Secured("ROLE_USER")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView eventList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "sort", defaultValue = "newest") String sort,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		ModelAndView mav = new ModelAndView("eventList");

		/*
		 * Sort Validation
		 */
		PageSort sortEnum;
		try {
			sortEnum = PageSort.valueOf(sort.toUpperCase());
		} catch (IllegalArgumentException e) {
			sortEnum = PageSort.NEWEST;
		}

		/*
		 * Page and count validation will take place in the repository
		 */
		List<Event> retrievedEventList = eventRepository.retrieveEventList(page, sortEnum, count);

		mav.addObject("eventList", retrievedEventList);

		return mav;
	}

	/**
	 * Sets up page to display details of an event
	 * 
	 * @param eventId
	 *            of event
	 * @return {@link ModelAndView}
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
	public ModelAndView eventDetails(@PathVariable(value = "eventId") long eventId) {
		ModelAndView mav = new ModelAndView("event");

		Event retrievedEvent = eventRepository.retrieveEventDetail(eventId);

		mav.addObject("event", retrievedEvent);

		return mav;
	}

	// TODO: Correct the mapping of this
	// TODO: Need validation and CSRF
	@RequestMapping(value = "/{eventId}/register", method = RequestMethod.POST)
	public ModelAndView eventRegister(@PathVariable(value = "eventId") long eventId,
			@RequestParam(value = "userId") long userId) {
		ModelAndView mav = new ModelAndView("redirect: /events?eventId=" + eventId);

		eventRepository.registerForEvent(eventId, userId);

		return mav;
		// TODO: Add flashattribute to determine confirmation. Then have
		// notify.js confirmation
	}
}

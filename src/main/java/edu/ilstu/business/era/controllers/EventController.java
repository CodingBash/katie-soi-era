package edu.ilstu.business.era.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ilstu.business.era.constants.PageSort;
import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.repositories.EventRepository;
import edu.ilstu.business.era.repositories.UserRepository;
import edu.ilstu.business.era.utilities.LeastPointsComparator;
import edu.ilstu.business.era.utilities.MostPointsComparator;
import edu.ilstu.business.era.utilities.NewestEventComparator;
import edu.ilstu.business.era.utilities.OldestEventComparator;

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
	 * Repository to get user data
	 */
	@Autowired
	private UserRepository userRepository;

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
		 * Page and count validation will take place in the repository
		 */
		List<Event> retrievedEventList = eventRepository.retrieveEventList(page, count);

		mav.addObject("eventList", retrievedEventList);

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
		 * Return page sort comparator
		 */
		switch (sortEnum) {
		case NEWEST:
			mav.addObject("comparator", new NewestEventComparator());
			break;
		case OLDEST:
			mav.addObject("comparator", new OldestEventComparator());
			break;
		case MOSTPOINTS:
			mav.addObject("comparator", new MostPointsComparator());
			break;
		case LEASTPOINTS:
			mav.addObject("comparator", new LeastPointsComparator());
			break;
		}

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

	// TODO: Accidental resubmit avoidance
	// TODO: Security in event registration. Get UserId from SecurityContext
	@Secured("ROLE_USER")
	@RequestMapping(value = "/{eventId}/register", method = RequestMethod.POST)
	public ModelAndView eventRegister(@PathVariable(value = "eventId") long eventId,
			final RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView mav = new ModelAndView("redirect:/events/" + eventId);

		try {
			eventRepository.registerForEvent(eventId, userRepository.getUserIdFromUsername(principal.getName()));
			redirectAttributes.addFlashAttribute("registrationSuccess", true);
		} catch (KatieResourceNotFoundException krnfe) {
			redirectAttributes.addFlashAttribute("registrationSuccess", false);
			redirectAttributes.addFlashAttribute("registrationError", "Error registering for event: user not found");
		} catch (KatieActionFailedException kafe) {
			redirectAttributes.addFlashAttribute("registrationSuccess", false);
			redirectAttributes.addFlashAttribute("registrationError",
					"Error registering for event: registration failure; already registered or expired event?");
		}

		return mav;
	}
}

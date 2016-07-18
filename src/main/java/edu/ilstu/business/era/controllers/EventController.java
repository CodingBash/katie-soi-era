package edu.ilstu.business.era.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
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

import edu.ilstu.business.era.comparators.LeastPointsComparator;
import edu.ilstu.business.era.comparators.MostPointsComparator;
import edu.ilstu.business.era.comparators.NewestEventComparator;
import edu.ilstu.business.era.comparators.OldestEventComparator;
import edu.ilstu.business.era.constants.PageSort;
import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.repositories.ClassRepository;
import edu.ilstu.business.era.repositories.EventRepository;
import edu.ilstu.business.era.repositories.UserRepository;

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
	 * Repository to get class data
	 */
	@Autowired
	private ClassRepository classRepository;

	/**
	 * Sets up page to display a {@link List} of {@link Event}
	 * 
	 * @param eventId
	 *            of first {@link Event}
	 * @param count
	 *            of amount of {@link Event}s
	 * @return {@link ModelAndView}
	 */
	// TODO: Verify buCode to protect from malicious request code
	@Secured("ROLE_USER")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView eventList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "sort", defaultValue = "newest") String sort, Principal principal) {
		ModelAndView mav = new ModelAndView("eventList");

		/*
		 * Page and count validation will take place in the repository
		 */
		List<Event> retrievedEventList = eventRepository.retrieveEventList();

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
		 * Sort the list
		 */
		Comparator<Event> eventSortComparator = null;
		switch (sortEnum) {
		case NEWEST:
			eventSortComparator = new NewestEventComparator();
			break;
		case OLDEST:
			eventSortComparator = new OldestEventComparator();
			break;
		case MOSTPOINTS:
			eventSortComparator = new MostPointsComparator();
			break;
		case LEASTPOINTS:
			eventSortComparator = new LeastPointsComparator();
			break;
		}

		if (eventSortComparator != null) {
			Collections.sort(retrievedEventList, eventSortComparator);
		}

		mav.addObject("eventList", retrievedEventList);
		mav.addObject("eventListSize", retrievedEventList.size());
		mav.addObject("principalUsername", principal.getName());

		return mav;
	}

	/**
	 * Sets up page to display details of an event
	 * 
	 * @param eventId
	 *            of event
	 * @return {@link ModelAndView}
	 */
	// TODO: Find a way to attach files to event from repo
	@Deprecated
	@Secured("ROLE_USER")
	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
	public ModelAndView eventDetails(@PathVariable(value = "eventId") String eventId, Principal principal) {
		ModelAndView mav = new ModelAndView("event");

		/*
		 * Get refId/ULID from the principal
		 */
		String refId = principal.getName();

		/*
		 * Get the buCode from the refId
		 */
		String buCode = classRepository.getBuCode(refId);

		/*
		 * Get the event from the repository
		 */
		Event retrievedEvent = eventRepository.retrieveEventDetail(buCode, eventId);

		/*
		 * Add event to the response
		 */
		mav.addObject("event", retrievedEvent);

		return mav;
	}

	// TODO: Accidental resubmit avoidance
	// TODO: Security in event registration. Get UserId from SecurityContext
	@Secured("ROLE_USER")
	@RequestMapping(value = "/{eventId}/register", method = RequestMethod.POST)
	public ModelAndView eventRegister(@PathVariable(value = "eventId") String eventId,
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

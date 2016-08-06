package edu.ilstu.business.era.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.business.era.comparators.LeastPointsComparator;
import edu.ilstu.business.era.comparators.MostPointsComparator;
import edu.ilstu.business.era.comparators.NewestEventComparator;
import edu.ilstu.business.era.comparators.OldestEventComparator;
import edu.ilstu.business.era.constants.PageSort;
import edu.ilstu.business.era.exceptions.KatieActionFailedException;
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
public class EventController
{

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
			@RequestParam(value = "sort", defaultValue = "newest") String sort, Principal principal)
	{
		ModelAndView mav = new ModelAndView("eventList");

		/*
		 * Page and count validation will take place in the repository
		 */
		List<Event> retrievedEventList = eventRepository.retrieveEventList();

		/*
		 * Sort Validation
		 */
		PageSort sortEnum;
		try
		{
			sortEnum = PageSort.valueOf(sort.toUpperCase());
		} catch (IllegalArgumentException e)
		{
			sortEnum = PageSort.NEWEST;
		}

		/*
		 * Sort the list
		 */
		Comparator<Event> eventSortComparator = null;
		switch (sortEnum)
		{
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

		if (eventSortComparator != null)
		{
			Collections.sort(retrievedEventList, eventSortComparator);
		}

		mav.addObject("eventList", retrievedEventList);
		mav.addObject("eventListSize", retrievedEventList.size());
		mav.addObject("principalUsername", principal.getName());

		return mav;
	}

	// @Secured("ROLE_USER")
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
	public ModelAndView eventDetails(@PathVariable(value = "eventId") String eventId, Principal principal)
	{
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


	@Secured("ROLE_USER")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> eventRegistration(@RequestParam(value = "classId") String classId,
			@RequestParam(value = "eventId") String eventId, Principal principal)
	{
		try
		{
			// TODO: Fix DateTime
			eventRepository.registerForEvent(getPrincipalName(principal), eventId,
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ").format(new Date()), classId);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (KatieActionFailedException kafe)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/unregister", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> eventRegistration(@RequestParam(value = "eventId") String eventId,
			Principal principal)
	{
		try
		{
			eventRepository.unregisterForEvent(eventId, getPrincipalName(principal));
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (KatieActionFailedException kafe)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/registeredevents", method = RequestMethod.GET)
	public ModelAndView registeredEvents(Principal principal)
	{
		ModelAndView mav = new ModelAndView("registeredEvents");
		List<Event> registeredEventList = eventRepository.retrieveRegisteredEventList(getPrincipalName(principal));
		mav.addObject("registeredEventList", registeredEventList);
		return mav;

	}

	// TEST
	@Deprecated
	@Secured("ROLE_USER")
	@RequestMapping(value = "/testregistration", method = RequestMethod.GET)
	public ModelAndView testRegistration(@RequestParam(value = "classId") String classId,
			@RequestParam(value = "eventId") String eventId, Principal principal)
	{
		System.out.println("TEST REGISTRATION (IN EVENTCONTROLLER)");
		System.out.println("USERID: " + getPrincipalName(principal));
		System.out.println("EVENTID: " + eventId);
		System.out.println("CLASSID: " + classId);

		// TODO: Fix DateTime
		eventRepository.registerForEvent(getPrincipalName(principal), eventId,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ").format(new Date()), classId);
		return new ModelAndView("eventList");
	}

	// TEST
	@Deprecated
	@Secured("ROLE_USER")
	@RequestMapping(value = "/testunregistration", method = RequestMethod.GET)
	public ModelAndView testUnregistration(@RequestParam(value = "eventId") String eventId, Principal principal)
	{
		System.out.println("TEST UNREGISTRATION (IN EVENTCONTROLLER)");
		System.out.println("USERID: " + getPrincipalName(principal));
		System.out.println("EVENTID: " + eventId);
		eventRepository.unregisterForEvent(eventId, getPrincipalName(principal));
		return new ModelAndView("eventList");
	}

	private String getPrincipalName(Principal principal)
	{
		return userRepository.getUserIdFromUsername(principal.getName());
	}
}

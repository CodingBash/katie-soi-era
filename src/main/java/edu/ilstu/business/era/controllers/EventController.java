package edu.ilstu.business.era.controllers;

import static edu.ilstu.business.era.constants.ApplicationConstants.DATE_FORMAT;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.business.era.models.Event;
import edu.ilstu.business.era.repositories.EventRepository;

/**
 * Controls event mappings
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@RequestMapping("/events")
@Controller
public class EventController
{

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	/**
	 * Repository to get event data
	 */
	@Autowired
	private EventRepository eventRepository;

	/**
	 * Controller to setup event-list page view
	 * 
	 * @param principal
	 *            for retrieving user data
	 * @return
	 */
	@Secured("ROLE_USER")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView eventList(Principal principal)
	{
		logger.debug("EventController#eventList(Principal) called");

		/*
		 * Create MaV and set the view
		 */
		ModelAndView mav = new ModelAndView("eventList");

		/*
		 * Retrieve necessary data
		 */
		List<Event> retrievedEventList = eventRepository.retrieveEventList();
		List<Event> registeredEventList = eventRepository.retrieveRegisteredEventList(getUserIdentification(principal));

		/*
		 * Add data to view
		 */
		mav.addObject("registeredEventList", registeredEventList);
		mav.addObject("eventList", retrievedEventList);
		mav.addObject("upcomingEventListSize", getUpcomingEventListSize(retrievedEventList));
		mav.addObject("principalUsername", principal.getName());

		return mav;
	}

	/**
	 * 
	 * @param principal
	 * @return
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/registered", method = RequestMethod.GET)
	public ModelAndView registeredEventList(Principal principal)
	{
		logger.debug("EventController#registeredEventList(Principal) called");

		ModelAndView mav = new ModelAndView("registeredEventList");
		List<Event> registeredEventList = eventRepository.retrieveRegisteredEventList(getUserIdentification(principal));
		mav.addObject("registeredEventList", registeredEventList);
		return mav;

	}

	/**
	 * Register a user for an event
	 * 
	 * @param classId
	 *            class ID of event
	 * @param eventId
	 *            event ID of event
	 * @param principal
	 *            for retrieving user data
	 * @return {@link ResponseEntity} determining if registration was successful
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> eventRegistration(@RequestParam(value = "classId") String classId,
			@RequestParam(value = "eventId") String eventId, Principal principal)
	{
		logger.debug("EventController#eventRegistration(String, String, Principal) called");

		// TODO: Validation on date. Add security log if validation failed
		try
		{
			eventRepository.registerForEvent(getUserIdentification(principal), eventId,
					new SimpleDateFormat(DATE_FORMAT)
							.format(eventRepository.retrieveEventDetail(eventId, classId).getStartDate()),
					classId);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e)
		{
			logger.warn("Unable to register:" + eventId + "|" + getUserIdentification(principal) + "|" + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	/**
	 * Unregister a user for an event
	 * 
	 * @param eventId
	 *            event ID of event
	 * @param principal
	 *            for retrieving user data
	 * @return {@link ResponseEntity} determine if unregistration was successful
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/unregister", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> eventRegistration(@RequestParam(value = "eventId") String eventId,
			Principal principal)
	{
		logger.debug("EventController#registeredEventList(String, Principal) called");

		// TODO: Validation on date. Add security log if validation failed.
		try
		{
			eventRepository.unregisterForEvent(eventId, getUserIdentification(principal));
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e)
		{
			logger.warn("Unable to unregister:" + eventId + "|" + getUserIdentification(principal) + "|" + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	/**
	 * Get user identification
	 * 
	 * @param principal
	 *            for retrieving user identification
	 * @return user identification
	 */
	private String getUserIdentification(Principal principal)
	{
		return principal.getName();
	}

	private int getUpcomingEventListSize(List<Event> eventList)
	{
		if (!CollectionUtils.isEmpty(eventList))
		{
			Date currentDate = new GregorianCalendar(2016, Calendar.JULY, 6).getTime();
			int counter = 0;
			for (Event event : eventList)
			{
				if (currentDate.before(event.getStartDate()))
				{
					counter++;
				}
			}
			return counter;
		} else
		{
			return 0;
		}

	}
}

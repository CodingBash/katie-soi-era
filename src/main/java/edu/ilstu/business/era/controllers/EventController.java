package edu.ilstu.business.era.controllers;

import static edu.ilstu.business.era.constants.ApplicationConstants.DATE_FORMAT;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.business.era.models.Event;
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
		mav.addObject("eventListSize", retrievedEventList.size());
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
		try
		{
			eventRepository.registerForEvent(getUserIdentification(principal), eventId, new SimpleDateFormat(DATE_FORMAT)
					.format(eventRepository.retrieveEventDetail(eventId, classId).getStartDate()), classId);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e)
		{
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
		try
		{
			eventRepository.unregisterForEvent(eventId, getUserIdentification(principal));
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	private String getUserIdentification(Principal principal)
	{
		// TODO: Determine if user ID or username
		return userRepository.getUserIdFromUsername(principal.getName());
	}
}

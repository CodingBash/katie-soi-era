package edu.ilstu.business.era.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 */
@Controller
public class HomeController
{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Handles the home page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home()
	{
		logger.debug("HomeController#home() called");
		
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	/**
	 * Handles login mapping
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login()
	{
		logger.debug("HomeController#login() called");
		
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
}

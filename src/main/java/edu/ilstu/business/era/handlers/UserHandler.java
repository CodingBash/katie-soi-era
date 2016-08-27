package edu.ilstu.business.era.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import edu.ilstu.business.era.delegates.ValidationDelegate;
import edu.ilstu.business.era.exceptions.KatieValidationException;
import edu.ilstu.business.era.repositories.UserDetailsAuthenticationRepository;

/**
 * Handles user detail retrieval
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@Component
public class UserHandler implements UserDetailsService
{
	private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);


	@Autowired
	private ValidationDelegate validationDelegate;
	@Autowired
	private UserDetailsAuthenticationRepository userDetailsAuthenticationRepository;


	/**
	 * Retrieves {@link UserDetails} from a {@link String} username
	 * 
	 * @param username
	 *            username
	 * @return {@link UserDetails}
	 */
	public UserDetails loadUserByUsername(String username) throws RuntimeException
	{
		logger.info("UserDelegate#loadUserByUsername(String) called: username=" + username);

		/*
		 * Input validation
		 */
		try
		{
			username = validationDelegate.validateUsername(username);
		} catch (KatieValidationException kve)
		{
			logger.warn("Unable to validate format: exception=" + kve.getMessage());

			throw kve;
		} catch (Exception e)
		{
			logger.warn("Unknown exception when validating: exception=" + e.getMessage());

			throw e;
		}

		/*
		 * Load user details
		 */
		try
		{
			return userDetailsAuthenticationRepository.loadUserByUsername(username);
		} catch (UsernameNotFoundException unfe)
		{
			logger.warn("Username not found in system: exception=" + unfe.getMessage());

			throw unfe;
		}

	}
}

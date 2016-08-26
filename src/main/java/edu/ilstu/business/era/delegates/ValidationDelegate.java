package edu.ilstu.business.era.delegates;

import java.util.Date;

import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ilstu.business.era.exceptions.KatieValidationException;
import edu.ilstu.business.era.repositories.EventRepository;

/**
 * Handles validation among application
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@Component
public class ValidationDelegate
{

	private static final Logger logger = LoggerFactory.getLogger(ValidationDelegate.class);

	@Autowired
	private EventRepository eventRepository;

	// TODO: Any other validations? Size? Specific days before? Holds?
	/**
	 * Validation of event registration and unregistration
	 * 
	 * @param classId
	 *            class ID
	 * @param eventId
	 *            event ID
	 * @throws KatieValidationException
	 *             if event has passed (current date > event starting date)
	 */
	public void validateEventRegistration(String classId, String eventId) throws KatieValidationException
	{
		logger.info("ValidationDelegate#validateEventRegistrationUpdate(String, String) called: classId=" + classId
				+ " | eventId=" + eventId);

		Date eventDate = eventRepository.retrieveEventDetail(eventId, classId).getStartDate();
		Date currentDate = new Date();
		if (currentDate.after(eventDate))
		{
			throw new KatieValidationException("Attempted to register for an event that already passed: currentDate="
					+ currentDate.toString() + "| eventDate=" + eventDate.toString());
		}
	}

	/**
	 * Validate class ID for security and format risk
	 * 
	 * @param classId
	 *            class ID
	 * @return sanitized class ID
	 * @throws KatieValidationException
	 *             if validation failed
	 */
	public String validateClassId(String classId) throws KatieValidationException
	{
		logger.info("ValidationDelegate#validateClassId(String) called: classId=" + classId);

		String sanitizedClassId = ESAPI.encoder().canonicalize(classId);

		if (!ESAPI.validator().isValidInput("Class ID validation", sanitizedClassId, "ClassID", 100, false))
		{
			throw new KatieValidationException("Could not validate class ID: classId=" + sanitizedClassId);
		} else
		{
			return sanitizedClassId;
		}
	}

	/**
	 * Validate event ID for security and format risk
	 * 
	 * @param eventId
	 *            event ID
	 * @return sanitized event ID
	 * @throws KatieValidationException
	 *             if validation failed
	 */
	public String validateEventId(String eventId) throws KatieValidationException
	{
		logger.info("ValidationDelegate#validateEventId(String) called: eventId=" + eventId);
		String santitizedEventId = ESAPI.encoder().canonicalize(eventId);

		if (!ESAPI.validator().isValidInput("Event ID validation", santitizedEventId, "EventID", 100, false))
		{
			throw new KatieValidationException("Could not validate event ID: eventId=" + santitizedEventId);
		} else
		{
			return santitizedEventId;
		}
	}

	/**
	 * Validate username for security and format risk
	 * 
	 * @param username
	 *            username
	 * @return sanitized username
	 * @throws KatieValidationException
	 *             if validation failed
	 */
	public String validateUsername(String username) throws KatieValidationException
	{
		logger.info("ValidationDelegate#validateUsername(String) called: username=" + username);
		String sanitizedUsername = ESAPI.encoder().canonicalize(username);
		if (!ESAPI.validator().isValidInput("Username validation", sanitizedUsername, "Username", 100, false))
		{
			throw new KatieValidationException("Could not validate username: username=" + sanitizedUsername);
		} else
		{
			return sanitizedUsername;
		}
	}
}

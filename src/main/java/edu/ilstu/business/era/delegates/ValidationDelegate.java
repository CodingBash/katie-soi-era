package edu.ilstu.business.era.delegates;

import java.util.Date;

import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ilstu.business.era.exceptions.KatieValidationException;
import edu.ilstu.business.era.repositories.EventRepository;

@Component
public class ValidationDelegate
{

	@Autowired
	private EventRepository eventRepository;

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
	public void validateEventRegistrationUpdate(String classId, String eventId) throws KatieValidationException
	{
		Date eventDate = eventRepository.retrieveEventDetail(classId, eventId).getStartDate();
		Date currentDate = new Date();
		if (currentDate.after(eventDate))
		{
			throw new KatieValidationException();
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
		String sanitizedClassId = ESAPI.encoder().canonicalize(classId);

		// TODO: Add regex to ESAPI.properties
		if (!ESAPI.validator().isValidInput("Class ID validation", sanitizedClassId, "ClassID", 100, false))
		{
			throw new KatieValidationException();
		}

		return sanitizedClassId;
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
		String santitizedEventId = ESAPI.encoder().canonicalize(eventId);

		// TODO: Add regex to ESAPI.properties
		if (!ESAPI.validator().isValidInput("Event ID validation", santitizedEventId, "EventID", 100, false))
		{
			throw new KatieValidationException();
		}

		return santitizedEventId;
	}
}

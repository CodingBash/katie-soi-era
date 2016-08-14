package edu.ilstu.business.era.exceptions;

/**
 * Exception when a security risk is detected
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
public class KatieValidationException extends RuntimeException
{
	private static final long serialVersionUID = 1456100247914934758L;

	public KatieValidationException()
	{
		super();
	}

	public KatieValidationException(String message)
	{
		super(message);
	}

}

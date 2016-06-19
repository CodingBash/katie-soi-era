package edu.ilstu.business.era.exceptions;

/**
 * Exception when a REST action fails
 * 
 * @author Basheer
 *
 */
public class KatieActionFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7740058716449100133L;

	public KatieActionFailedException() {
		super();
	}

	public KatieActionFailedException(String message) {
		super(message);
	}

}

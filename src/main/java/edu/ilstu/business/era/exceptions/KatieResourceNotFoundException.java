package edu.ilstu.business.era.exceptions;

/**
 * Exception when a REST resource is unavailable
 * 
 * @author Basheer
 *
 */
public class KatieResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7693020729959119570L;

	public KatieResourceNotFoundException(){
		super();
	}
	
	public KatieResourceNotFoundException(String message){
		super(message);
	}
}

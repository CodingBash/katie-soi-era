package edu.ilstu.business.era.exceptions;

public class KatieSecurityRiskException extends RuntimeException
{
	private static final long serialVersionUID = 1456100247914934758L;

	public KatieSecurityRiskException(){
		super();
	}
	
	public KatieSecurityRiskException(String message){
		super(message);
	}
	
}

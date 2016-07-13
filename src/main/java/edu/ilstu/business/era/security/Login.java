package edu.ilstu.business.era.security;

import org.jasypt.digest.StandardStringDigester;

public class Login {
	
	private static StandardStringDigester digester = null;
	
	/*
	 * Input - the password entered by the user.
	 * disgest - the actual encrypted password stored in the LoudCloud. You can get this my querying the following URL
	 * https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/user/rsaripa
	 * under user-management/Retrive User. 
	 * The field we need is the json response is the "password"
	 *
	 */
	public static boolean checkPassword(String input,String digest){
		
		digester = new StandardStringDigester();
		digester.setAlgorithm("SHA-256");
		digester.setSaltSizeBytes(16);
		digester.setIterations(1000);
		
		boolean match = digester.matches(input, digest);
		
		return match;
	}
}

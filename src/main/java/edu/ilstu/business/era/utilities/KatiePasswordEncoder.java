package edu.ilstu.business.era.utilities;

import org.jasypt.digest.StandardStringDigester;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Encodes the credentials for the login system. Uses StringDigester, NOT ESAPI
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
public class KatiePasswordEncoder implements PasswordEncoder
{

	@Override
	public String encode(CharSequence rawPassword)
	{
		StandardStringDigester digester = getDigester();

		return digester.digest(rawPassword.toString());

	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		StandardStringDigester digester = getDigester();
		return digester.matches(rawPassword.toString(), encodedPassword);
	}

	public StandardStringDigester getDigester()
	{
		StandardStringDigester digester = new StandardStringDigester();
		digester = new StandardStringDigester();
		digester.setAlgorithm("SHA-256");
		digester.setSaltSizeBytes(16);
		digester.setIterations(1000);
		return digester;
	}
}

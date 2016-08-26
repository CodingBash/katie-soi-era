package edu.ilstu.business.era.utilities;

import org.jasypt.digest.StandardStringDigester;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Encodes the credentials for the login system
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
		// TODO: Get algorithm from EPASI
		digester.setAlgorithm("SHA-256");
		// TODO: Get salt size from EPASI
		digester.setSaltSizeBytes(16);
		// TODO: Get iterations from EPASI
		digester.setIterations(1000);
		return digester;
	}
}

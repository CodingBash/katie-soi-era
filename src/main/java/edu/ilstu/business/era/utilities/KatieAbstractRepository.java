package edu.ilstu.business.era.utilities;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;

@PropertySource("classpath:restauthorization.properties")
public abstract class KatieAbstractRepository {

	@Value("${loudcloud.username}")
	private String username;

	@Value("${loudcloud.password}")
	private String password;

	protected HttpHeaders createHeaders() {
		return new HttpHeaders() {

			private static final long serialVersionUID = -3471737976490328738L;

			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
}

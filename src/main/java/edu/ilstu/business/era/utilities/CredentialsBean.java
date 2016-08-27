package edu.ilstu.business.era.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:restauthorization.properties")
public class CredentialsBean {

	@Value("${loudcloud.username}")
	private String username;

	@Value("${loudcloud.password}")
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}

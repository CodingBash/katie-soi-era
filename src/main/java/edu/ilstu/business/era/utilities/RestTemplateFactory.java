package edu.ilstu.business.era.utilities;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource("classpath:restauthorization.properties")
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {
	private RestTemplate restTemplate;

	@Value("${loudcloud.username}")
	private String username;

	@Value("${loudcloud.password}")
	private String password;

	public RestTemplate getObject() {
		return restTemplate;
	}

	public Class<RestTemplate> getObjectType() {
		return RestTemplate.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() {
		System.out.println(username);
		System.out.println(password);
		HttpHost host = new HttpHost("localhost", 8080, "http");
		final AuthHttpComponentsClientHttpRequestFactory requestFactory = new AuthHttpComponentsClientHttpRequestFactory(
				host, "username", "password");
		restTemplate = new RestTemplate(requestFactory);
	}
}
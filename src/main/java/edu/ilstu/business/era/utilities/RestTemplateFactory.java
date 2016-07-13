package edu.ilstu.business.era.utilities;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Used to create {@link RestTemplate} with default settings
 * 
 * @author Basheer
 *
 */
@Deprecated
@Component
@PropertySource("classpath:restauthorization.properties")
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {
	private RestTemplate restTemplate;

	@Value("${loudcloud.username}")
	private String username;

	@Value("${loudcloud.password}")
	private String password;

	@Override
	public RestTemplate getObject() {
		return restTemplate;
	}

	@Override
	public Class<RestTemplate> getObjectType() {
		return RestTemplate.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() {
		HttpHost host = new HttpHost("localhost", 8080, "http");
		final AuthHttpComponentsClientHttpRequestFactory requestFactory = new AuthHttpComponentsClientHttpRequestFactory(
				host, username, password);
		restTemplate = new RestTemplate(requestFactory);
	}
}
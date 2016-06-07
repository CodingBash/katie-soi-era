package edu.ilstu.business.era.repositories;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClassRepositoryImpl implements ClassRepository {

	@Override
	public String getBuCode(String refId) {
		RestTemplate restTemplate = new RestTemplate();
		String getBuCodeUrl = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/{refId}";
		String buCode = restTemplate.getForObject(getBuCodeUrl, String.class, refId);
		return buCode;
	}

}

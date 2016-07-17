package edu.ilstu.business.era.repositories;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.ilstu.business.era.utilities.RestTemplateFactory;

@Component
public class ClassRepositoryImpl extends KatieAbstractRepository implements ClassRepository{

	@Autowired
	private RestTemplateFactory restTemplateFactory;

	private static final String GET_BU_CODE_URL = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/{refId}";

	@Override
	public String getBuCode(String refId) {

		final RestTemplate restTemplate = restTemplateFactory.getObject();

		ResponseEntity<String> jsonString = restTemplate.exchange(GET_BU_CODE_URL, HttpMethod.GET, new HttpEntity<Object>(createHeaders()), String.class,
				refId);
		
		ObjectNode node = null;
		String buCode = null;
		try {
			node = new ObjectMapper().readValue(jsonString.getBody(), ObjectNode.class);
			buCode = node.get("buCode").toString();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buCode;
	}

}

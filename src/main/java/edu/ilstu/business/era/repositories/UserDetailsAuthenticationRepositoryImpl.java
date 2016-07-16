package edu.ilstu.business.era.repositories;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;
import edu.ilstu.business.era.mappers.UserMapper;
import edu.ilstu.business.era.transferobjects.UserTO;
import edu.ilstu.business.era.utilities.KatieAbstractRepository;

public class UserDetailsAuthenticationRepositoryImpl extends KatieAbstractRepository
		implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	private static final String RETRIEVE_USER = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/user/{refId}";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*
		 * Get User response
		 */
		final RestTemplate restTemplate = new RestTemplate();
		Map<String, String> urlVariablesMap = new HashMap<String, String>();
		urlVariablesMap.put("refId", username.toLowerCase());
		ResponseEntity<String> jsonStringResponseUserTo = restTemplate.exchange(RETRIEVE_USER, HttpMethod.GET,
				new HttpEntity<Object>(createHeaders()), new ParameterizedTypeReference<String>() {
				}, urlVariablesMap);
		String jsonStringUserTo = jsonStringResponseUserTo.getBody();
		Type userToType = new TypeToken<UserTO>() {
		}.getType();
		UserTO userTo = new Gson().fromJson(jsonStringUserTo, userToType);
		
		System.out.println(userTo.getPassword());
		
		/*
		 * Create UserDetail
		 */
		if (userTo != null) {
			edu.ilstu.business.era.models.User user = userMapper.mapUserFromUserTO(userTo);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					authorities);
		}

		throw new KatieResourceNotFoundException("Username not found6");
	}

}

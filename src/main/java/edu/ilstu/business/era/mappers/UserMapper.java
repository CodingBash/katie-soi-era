package edu.ilstu.business.era.mappers;

import org.springframework.stereotype.Component;

import edu.ilstu.business.era.models.User;
import edu.ilstu.business.era.transferobjects.UserTO;

@Component
public class UserMapper {

	public User mapUserFromUserTO(UserTO userTo) {
		User user = new User();
		user.setEmail(userTo.getEmailAddress());
		user.setUsername(userTo.getUserName());
		user.setPassword(userTo.getPassword());
		// TODO: Complete mapping
		return user;
	}
	
}

package edu.ilstu.business.era.mappers;

import org.springframework.stereotype.Component;

import edu.ilstu.business.era.models.User;
import edu.ilstu.business.era.transferobjects.UserTO;

@Component
public class UserMapper {

	public User mapUserFromUserTO(UserTO userTo) {
		User user = new User();
		user.setPassword(userTo.getPassword());
		user.setUsername(userTo.getUserName());
		user.setUserId(userTo.getUserId());
		user.setRefId(userTo.getRefId());
		user.setFirstName(userTo.getFirstName());
		user.setLastName(userTo.getLastName());
		user.setEmailAddress(userTo.getEmailAddress());
		user.setBuCodes(userTo.getBuCodes());
		return user;
	}
	
}

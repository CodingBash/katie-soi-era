package edu.ilstu.business.era.mappers;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import edu.ilstu.business.era.models.User;
import edu.ilstu.business.era.transferobjects.UserTO;

/**
 * {@link UserMapper} test
 * 
 * @see UserMapper
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest
{
	public static final String PASSWORD = "password";
	public static final String USERNAME = "username";
	public static final String USERID = "user1";
	public static final String REFID = "refId";
	public static final String FIRSTNAME = "first";
	public static final String LASTNAME = "last";
	public static final String EMAIL = "test@example.com";
	public static final List<String> BUCODES = new ArrayList<String>();

	private UserMapper userMapper;

	/**
	 * Initialize the {@link UserMapper}
	 */
	@Before
	public void init()
	{
		userMapper = new UserMapper();
	}

	/**
	 * @method {@link UserMapper#mapUserFromUserTO(UserTO)}
	 * @objective verify if mapping works
	 * @expectedResults mapping works as expected
	 */
	@Test
	public void mapUserFromUserTO()
	{
		User expectedUser = createUser();
		User actualUser = userMapper.mapUserFromUserTO(createUserTo());
		assertTrue(EqualsBuilder.reflectionEquals(expectedUser, actualUser));
	}

	/**
	 * Create the expected {@link User} that will be returned from the
	 * {@link UserMapper#mapUserFromUserTO(UserTO)} method
	 * 
	 * @return {@link User}
	 */
	public User createUser()
	{
		User user = new User();
		user.setPassword(PASSWORD);
		user.setUsername(USERNAME);
		user.setUserId(USERID);
		user.setRefId(REFID);
		user.setFirstName(FIRSTNAME);
		user.setLastName(LASTNAME);
		user.setEmailAddress(EMAIL);
		user.setBuCodes(BUCODES);
		return user;
	}

	/**
	 * Create that {@link UserTO} that will be passed to
	 * {@link UserMapper#mapUserFromUserTO(UserTO)} and return the {@link User}
	 * created by {@link UserMapperTest#createUser()}
	 * 
	 * @return {@link UserTO}
	 */
	public UserTO createUserTo()
	{
		UserTO userTo = new UserTO();
		userTo.setPassword(PASSWORD);
		userTo.setUserName(USERNAME);
		userTo.setUserId(USERID);
		userTo.setRefId(REFID);
		userTo.setFirstName(FIRSTNAME);
		userTo.setLastName(LASTNAME);
		userTo.setEmailAddress(EMAIL);
		userTo.setBuCodes(BUCODES);
		return userTo;
	}
}

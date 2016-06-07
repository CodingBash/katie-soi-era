package edu.ilstu.business.era.repositories;

import org.springframework.stereotype.Component;

import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;

@Component
public class UserRepositoryImpl implements UserRepository {

	@Override
	public String getUserIdFromUsername(String username) throws KatieResourceNotFoundException {
		return "00825378-b376-41c7-a032-d6c8c93f1157";
	}

	@Override
	public String getUsernameFromUserId(long id) throws KatieResourceNotFoundException {
		return "Test";
	}

}

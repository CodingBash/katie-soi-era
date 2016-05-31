package edu.ilstu.business.era.repositories;

import org.springframework.stereotype.Component;

import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;

@Component
public class UserRepositoryImpl implements UserRepository {

	@Override
	public Long getUserIdFromUsername(String username) throws KatieResourceNotFoundException {
		return 55555L;
	}

	@Override
	public String getUsernameFromUserId(long id) throws KatieResourceNotFoundException {
		return "user";
	}

}

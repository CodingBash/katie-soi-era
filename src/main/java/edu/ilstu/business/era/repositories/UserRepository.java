package edu.ilstu.business.era.repositories;

import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;

public interface UserRepository {

	public Long getUserIdFromUsername(String username) throws KatieResourceNotFoundException;
	
	public String getUsernameFromUserId(long id) throws KatieResourceNotFoundException;
}

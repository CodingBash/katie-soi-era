package edu.ilstu.business.era.repositories;

import edu.ilstu.business.era.exceptions.KatieResourceNotFoundException;

/**
 * Repository interface for retrieving user information
 * 
 * @see UserRepositoryImpl
 * 
 * @author Basheer
 *
 */
public interface UserRepository {

	/**
	 * Retrieves user ID from a username
	 * 
	 * @see UserRepositoryImpl#getUserIdFromUsername(String)
	 * @param username
	 * @return
	 * @throws KatieResourceNotFoundException
	 */
	public String getUserIdFromUsername(String username) throws KatieResourceNotFoundException;

	/**
	 * Retrieves username from user ID
	 * 
	 * @see UserRepositoryImpl#getUsernameFromUserId(long)
	 * @param id
	 * @return
	 * @throws KatieResourceNotFoundException
	 */
	public String getUsernameFromUserId(long id) throws KatieResourceNotFoundException;
}

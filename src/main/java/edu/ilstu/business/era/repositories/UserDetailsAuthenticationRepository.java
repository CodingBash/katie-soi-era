package edu.ilstu.business.era.repositories;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Interface for repository for getting user authentication
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
public interface UserDetailsAuthenticationRepository
{
	/**
	 * Get UserDetails from the username
	 * 
	 * @param username
	 *            client username
	 * @return {@link UserDetails}
	 * @throws UsernameNotFoundException
	 *             if username does not exist in system
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

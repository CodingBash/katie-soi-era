package edu.ilstu.business.era.repositories;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsAuthenticationRepository  {
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

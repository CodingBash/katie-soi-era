package edu.ilstu.business.era.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import edu.ilstu.business.era.repositories.UserDetailsAuthenticationRepositoryImpl;
import edu.ilstu.business.era.utilities.KatiePasswordEncoder;

/**
 * Specific configuration of the security
 * 
 * @author Basheer
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsAuthenticationRepositoryImpl userDetailsAuthenticationRepositoryImpl;

	/**
	 * Configure the user store
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("guest")
		// .password("password").roles("GUEST").and().withUser("rsaripa").password("password").roles("USER");

		auth.userDetailsService(userDetailsAuthenticationRepositoryImpl).passwordEncoder(new KatiePasswordEncoder());

	}

	/**
	 * Configure the request mapping security
	 */
	// TODO: Verify that correct mapping is secured
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().rememberMe().tokenValiditySeconds(2419200).key("katieEraKey")
				.and().requiresChannel().and().logout().logoutSuccessUrl("/").logoutUrl("/logout");
	}

	/**
	 * Create the authentication manager bean
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

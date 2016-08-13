package edu.ilstu.business.era.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Specific configuration of the security
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	/**
	 * Configure the user store
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("guest")
				.password("password").roles("GUEST").and().withUser("rsaripa").password("password").roles("USER");
	}

	/**
	 * Configure the request mapping security
	 */
	// TODO: Verify that correct mapping is secured
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().rememberMe().tokenValiditySeconds(2419200).key("katieEraKey")
				.and().requiresChannel().and().logout().logoutSuccessUrl("/login");
	}

	/**
	 * Create the authentication manager bean
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
}

package edu.ilstu.business.era.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Configuring the development environment from H2 SQL scripts located in the
 * classpath.
 * 
 * @author Basheer
 *
 */
@Profile("devEnv")
@Configuration
public class DataConfigurationDevelopmentEnvironment {
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("db/sql/ksi_event.sql").addScript("db/sql/ksi_location.sql").addScript("db/sql/ksi_user_event.sql").addScript("db/sql/ksi_user.sql").build();
	}
	
}
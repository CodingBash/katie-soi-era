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
@Profile("developmentEnvironment")
@Configuration
public class DataConfigurationDevelopmentEnvironment {
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("db/sql/create-db.sql").build();
	}
}
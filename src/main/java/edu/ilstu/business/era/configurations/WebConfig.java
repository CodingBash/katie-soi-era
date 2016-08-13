package edu.ilstu.business.era.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import static edu.ilstu.business.era.constants.ApplicationConstants.PAGE_FOLDER;

/**
 * Default web configurer
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(
{ "edu.ilstu.business.era.configurations", "edu.ilstu.business.era.controllers", "edu.ilstu.business.era.database",
		"edu.ilstu.business.era.repositories", "edu.ilstu.business.era.mappers", "edu.ilstu.business.era.utilities" })
public class WebConfig extends WebMvcConfigurerAdapter
{

	/**
	 * Folder with views
	 */
	private static final String TEMPLATE_RESOLVER_PREFIX = "/WEB-INF/views/" + PAGE_FOLDER;

	/**
	 * View extension
	 */
	private static final String TEMPLATE_RESOLVER_SUFFIX = ".html";

	/**
	 * View type
	 */
	private static final String TEMPLATE_RESOLVER_TEMPLATE_MODE = "HTML5";

	// TODO: Update the datasource
	/**
	 * Set the datasource for the application
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource()
	{
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("db/sql/ksi_event.sql")
				.addScript("db/sql/mock_ksi_event.sql").addScript("db/sql/ksi_location.sql")
				.addScript("db/sql/mock_ksi_location.sql").addScript("db/sql/ksi_user_event.sql")
				.addScript("db/sql/ksi_user.sql").addScript("db/sql/mock_ksi_user.sql").build();
	}

	/**
	 * Configure view resolver bean
	 * 
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver()
	{
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}

	/**
	 * Configure the template engine
	 * 
	 * @return
	 */
	@Bean
	public SpringTemplateEngine templateEngine()
	{
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	/**
	 * Configure the template resolver
	 * 
	 * @return
	 */
	@Bean
	public TemplateResolver templateResolver()
	{
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix(TEMPLATE_RESOLVER_PREFIX);
		templateResolver.setSuffix(TEMPLATE_RESOLVER_SUFFIX);
		templateResolver.setTemplateMode(TEMPLATE_RESOLVER_TEMPLATE_MODE);
		return templateResolver;
	}

	/**
	 * Configure the property source
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * Send static resources to front-end
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/static/assets/");
	}

}

package edu.ilstu.business.era.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Default web configurer
 * 
 * @author Basheer
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "edu.ilstu.business.era.configurations", "edu.ilstu.business.era.controllers",
		"edu.ilstu.business.era.repositories", "edu.ilstu.business.era.mappers", "edu.ilstu.business.era.utilities" })
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * Folder with views
	 */
	private static final String TEMPLATE_RESOLVER_PREFIX = "/WEB-INF/views/prod/";

	/**
	 * View extension
	 */
	private static final String TEMPLATE_RESOLVER_SUFFIX = ".html";

	/**
	 * View type
	 */
	private static final String TEMPLATE_RESOLVER_TEMPLATE_MODE = "HTML5";

	/**
	 * Configure view resolver bean
	 * 
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
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
	public SpringTemplateEngine templateEngine() {
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
	public TemplateResolver templateResolver() {
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
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/static/assets/");
	}

}

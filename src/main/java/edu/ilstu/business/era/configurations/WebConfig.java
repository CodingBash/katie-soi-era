package edu.ilstu.business.era.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"edu.ilstu.business.era.configurations", "edu.ilstu.business.era.controllers", "edu.ilstu.business.era.repositories"})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * Configure the Thymeleaf view resolver using the Thymeleaf template engine
	 * from {@link BeanConfiguration#templateEngine(TemplateResolver)}
	 * 
	 * @param templateEngine
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine((SpringTemplateEngine) templateEngine());
		return viewResolver;
	}
	
	/**
	 * Configure the Thymeleaf template engine using the Thymeleaf view resolver
	 * from {@link BeanConfiguration#templateResolver()}
	 * 
	 * @param templateResolver
	 * @return
	 */
	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}
	
	/**
	 * Configure the Thymeleaf template resolver
	 * 
	 * @return
	 */
	@Bean
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/devBACK");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}




}

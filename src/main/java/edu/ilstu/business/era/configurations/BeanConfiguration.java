package edu.ilstu.business.era.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Configuration of common beans
 * 
 * @author Basheer
 *
 */
@Configuration
public class BeanConfiguration {

	/**
	 * Configure the Thymeleaf template resolver
	 * 
	 * @return
	 */
	@Bean
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}

	/**
	 * Configure the Thymeleaf template engine using the Thymeleaf view resolver
	 * from {@link BeanConfiguration#templateResolver()}
	 * 
	 * @param templateResolver
	 * @return
	 */
	@Bean
	public TemplateEngine templateEngine(TemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}

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
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}
}

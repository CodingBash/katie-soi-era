package edu.ilstu.business.era.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initialize web configuration
 * 
 * @author Basheer
 *
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

  @Override
  protected Class<?>[] getRootConfigClasses() {
	  return new Class<?>[] {RootConfiguration.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}

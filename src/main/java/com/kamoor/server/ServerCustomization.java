package com.kamoor.server;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;

/**
 * customize any server configuration
 * 
 * @author kamoorr
 * 
 */
public class ServerCustomization extends ServerProperties {

	static Logger logger = Logger.getLogger(ServerCustomization.class);

	

	/**
	 * This will override any properties loaded by application.properties. Be
	 * careful about this one
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer factory) {

		super.customize(factory);
		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
				"/~core/error.jsp"));
		factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
				"/~core/error.jsp"));
		factory.addErrorPages(new ErrorPage("/~core/error.jsp"));
	}

}

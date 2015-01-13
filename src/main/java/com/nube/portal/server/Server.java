package com.nube.portal.server;

import java.util.Arrays;

import javax.servlet.MultipartConfigElement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring boot to start server on the fly and run an app. Parameters: --debug to
 * start in debug mode nube-portal-custom.properties: should be used for custom
 * properties
 * 
 * @author kamoorr
 * 
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableCaching
@PropertySource(ignoreResourceNotFound = true, value = {
		"classpath:application.properties", 
		"classpath:admin.properties",
		"classpath:nube-portal.properties",
		"classpath:nube-portal-custom.properties" })
@ImportResource(value = { "classpath*:spring/*.xml" })
@EnableAspectJAutoProxy
public class Server {


	static Logger logger = Logger.getLogger(Server.class);

	public static void main(String[] args) {

		System.out.println("Start Nube cloud application manager");
		SpringApplication.run(Server.class, args);
	}

	/**
	 * Server configuration customization
	 * 
	 * @return
	 */
	@Bean
	public ServerProperties getServerProperties() {
		return new ServerCustomization();
	}


	@Bean(name = "cacheManager")
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager
				.setCaches(Arrays.asList(new ConcurrentMapCache("content")));

		return cacheManager;
	}

	/**
	 * Define a server. Using tomcat or jetty will be out of the box
	 * 
	 * @return
	 */
	// @Bean
	// public EmbeddedServletContainerFactory servletContainer() {
	// System.out.println("Reconfiguring servlet container to tomcat");
	// TomcatEmbeddedServletContainerFactory factory = new
	// TomcatEmbeddedServletContainerFactory();
	// factory.setPort(9000);
	// factory.setBaseDirectory(new File("target")); // look for work folder
	// factory.setContextPath("/api");
	//
	// factory.setSessionTimeout(30, TimeUnit.MINUTES);
	// factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
	// "/error_404.html"));
	//
	// //System.out.println("Base path"+ factory.getB.getAbsolutePath());
	//
	// return factory;
	// }

}

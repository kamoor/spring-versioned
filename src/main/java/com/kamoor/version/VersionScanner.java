package com.kamoor.version;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VersionScanner implements ApplicationContextAware{

	Logger logger = Logger.getLogger(this.getClass());
	
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		for(String beanName: applicationContext.getBeanDefinitionNames()){
			if(beanName.contains("springframework")){
				continue;
			}
			logger.info("Bean "+ beanName);
			Object obj = applicationContext.getBean(beanName);
			for(Method m: obj.getClass().getDeclaredMethods()){
				if(m.isAnnotationPresent(Versioned.class)){
					logger.info(beanName + " Found annotation "+ Arrays.toString(m.getAnnotations()));
				}
			}
		}	
	}

}

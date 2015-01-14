package com.poc.version;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VersionScanner implements ApplicationContextAware{

	
	Map<String, Method> versionedMap = new HashMap<String, Method>();
	
	Logger logger = Logger.getLogger(this.getClass());
	
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		for(String beanName: applicationContext.getBeanDefinitionNames()){
		
			if(applicationContext.getBean(beanName).getClass().getName().contains("springframework")){
				continue;
			}
			// logger.info("Bean "+ beanName);
			for(Method m: AopUtils.getTargetClass(applicationContext.getBean(beanName)).getDeclaredMethods()){
				if(m.isAnnotationPresent(Versioned.class)){
					
					versionedMap.put(m.getAnnotation(Versioned.class).feature() +":" +  m.getAnnotation(Versioned.class).version(), m);
				}
			}
		}	
	}
	
	
	public Map<String, Method> getVersionedMethods(){
		return versionedMap;
	}

}

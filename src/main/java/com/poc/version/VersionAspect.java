package com.poc.version;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * Execute for all method with @Versioned annotation
 * @author kamoorr
 *
 */
@Component
@Aspect
public class VersionAspect {
	
	
    	Logger logger = Logger.getLogger(VersionAspect.class);

		@Autowired
		VersionScanner versionScanner;
		
		@Autowired
		Environment env;

		
	    @Around("execution (* *.*(..)) && @annotation(ver)")
	    public Object versionedAction(
	        ProceedingJoinPoint pjp, Versioned ver)
	        throws Throwable {
	    
	    	String version = env.getProperty(ver.feature());
	    	if(version == null || version.equals(String.valueOf(ver.version()))){
	    		return pjp.proceed();
	    	}else{
	    		logger.info("Executing version "+ version);
	      		return versionScanner.getVersionedMethods().get(ver.feature() + ":" + version).invoke(pjp.getTarget(), pjp.getArgs());
	    	}
	    	
	        
	    }
	    
	    
	   
}

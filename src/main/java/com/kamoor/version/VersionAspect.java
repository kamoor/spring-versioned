package com.kamoor.version;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

	    @Around("execution (* *.*(..)) && @annotation(ver)")
	    public Object versionedAction(
	        ProceedingJoinPoint pjp, Versioned ver)
	        throws Throwable {
	    	logger.info("Running versioned annotations ");
	        return pjp.proceed();
	    }
	    
	    
	   
}

package com.geryon.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.geryon.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
	
	@AfterThrowing(pointcut = "execution(* com.geryon.repository.*Impl.*(..))", throwing = "exception")
	public void logRepositoryException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
}

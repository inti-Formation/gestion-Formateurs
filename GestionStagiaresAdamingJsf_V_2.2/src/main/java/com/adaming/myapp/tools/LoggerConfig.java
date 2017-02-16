package com.adaming.myapp.tools;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public class LoggerConfig {

	private static final Logger LOGGER = Logger.getLogger(LoggerConfig.class);

	public void logEntry(JoinPoint joinPoint) {
		logInfo("Entering " + joinPoint.getTarget().getClass().getSimpleName()
				+ " : " + joinPoint.getSignature().getName());

	}

	public void logExit(JoinPoint joinPoint, Object result) {
		if (!(result instanceof List || result instanceof Map)) {
			logInfo("Return value :" + result);
		}

		logInfo("Exiting  " + joinPoint.getTarget().getClass().getSimpleName()
				+ " : " + joinPoint.getSignature().getName());

	}

	public void logAfterThrowsAdvice(JoinPoint joinPoint, Throwable e) {
		logError("Exception "
				+ joinPoint.getTarget().getClass().getSimpleName() + " : "
				+ joinPoint.getSignature().getName());
		logError("Cause :" + e.getMessage());

	}

	public static void logInfo(String LogMessage) {
		LOGGER.info(LogMessage);
	}

	public static void logError(String LogMessage) {
		LOGGER.error(LogMessage);
	}
	public static void logDebug(String LogMessage) {
		LOGGER.debug(LogMessage);
	}
}

package com.epam.newsportal.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLogger.class);

    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage());
            throw throwable;
        }
    }
}

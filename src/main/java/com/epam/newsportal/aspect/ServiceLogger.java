package com.epam.newsportal.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLogger {
    private static Logger logger = LoggerFactory.getLogger(ServiceLogger.class);

    @Around("execution(* com.epam.newsportal.service.*.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage());
            throw throwable;
        }
    }
}

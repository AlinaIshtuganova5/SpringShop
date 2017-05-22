package com.itis.shop.business.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created on 17.05.17.
 */
@Aspect
@Component
public class LoggerServiceAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("within(com.itis.shop.business.service.*)")
    public void logAroundServiceMethods(JoinPoint joinPoint) throws Throwable {
        logger.info("method {} with arguments {} has be executed",
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
}

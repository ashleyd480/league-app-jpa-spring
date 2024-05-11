package com.example.leagueapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.leagueapp.service..*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Before method: " + joinPoint.getSignature().toShortString() + "; Trigger time: " + System.currentTimeMillis());
    }

    @After("serviceMethods()")
    public void logAfterMethod(JoinPoint joinPoint) {
        log.info("After method: " + joinPoint.getSignature().toShortString() + "; Trigger time: " + System.currentTimeMillis());
    }
}

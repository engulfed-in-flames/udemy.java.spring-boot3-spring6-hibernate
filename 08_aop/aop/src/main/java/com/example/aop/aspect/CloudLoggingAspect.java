package com.example.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
 * @Aspect
 * 
 * @Component
 * 
 * @Order(3) public class CloudLoggingAspect {
 * 
 * @Before(
 * "com.example.aop.aspect.AspectExpression.pointcutNotForGetterAndSetter()")
 * public void logToCloudAsync() {
 * 
 * System.out.println("===> Logging to cloud asynchronously"); } }
 */
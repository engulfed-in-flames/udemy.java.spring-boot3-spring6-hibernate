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
 * @Order(1) public class APIAnanlyticsAspect {
 * 
 * @Before(
 * "com.example.aop.aspect.AspectExpression.pointcutNotForGetterAndSetter()")
 * public void performAPIAnalytics() {
 * 
 * System.out.println("===> Performing API analytics."); } }
 */

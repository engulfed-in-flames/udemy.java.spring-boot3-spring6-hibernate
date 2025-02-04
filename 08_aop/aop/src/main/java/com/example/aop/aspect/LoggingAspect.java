package com.example.aop.aspect;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.aop.entity.Account;

/* 
 * @Order
 * 1. Range : Integer.MIN_VALUE ~ Integer.MAX_VALUE (Negative value allowed)
 * 2. Does not need to be consecutive
 * 3. If the order is the same, it's hard to know which one runs first.  
 */

@Aspect
@Component
@Order(2)
public class LoggingAspect {

//	@Before("execution (public void addAccount())")
//	@Before("execution (* add*(com.example.aop.entity.Account))")
//	@Before("execution (* add*(com.example.aop.entity.Account, ..))")
//	@Before("execution (* add*(..))")
//	@Before("execution (* com.example.aop.dao.AccountDAO.add*(..))")
//	@Before("execution (* com.example.aop.dao.*.*(..))") 
	public void adviceBeforeAddAccountV1() {
		System.out.println("===> @Before advice on addAccount()"); 
	}
	 

	
//	@Before("pointcutForGeneralPurpose()")
//	@Before("com.example.aop.aspect.AspectExpression.pointcutNotForGetterAndSetter()")
	public void adviceBeforeAddAccountV2() {
		System.out.println("===> Executing @Before advice v2 on addAccount()"); 
	}
	 
 	@Before("com.example.aop.aspect.AspectExpression.beforeNotForGetterAndSetter()")
	public void beforeAddAccountV3(JoinPoint joinPoint) {
		// JointPoint has metadata about the method.
		System.out.println("===> Executing @Before advice v2 on addAccount()");
 	
		// Display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method Signature: " + methodSignature);
		
		// Display the method arguments
		Object[] args = joinPoint.getArgs();
		
		for (Object arg : args) {
			if(arg instanceof Account) {
				Account account = (Account) arg;
				System.out.println("--- Account Info ----");
				System.out.println("Name : " + account.getName());
				System.out.println("Level : " + account.getLevel());
			} else {
				String string = arg.toString();
				System.out.println("Argument: " + string);
			}
		}
		System.out.println();
 	}
 	
 	@AfterReturning(
 			pointcut = "com.example.aop.aspect.AspectExpression.afterReturningForMethodFindAccounts()",
 			returning = "result")
 	public void afterReturningFindAccounts(JoinPoint joinPoint, List<Account> result) {
 		
 		System.out.println("---- @AfterReturning ----");
 		
		// Display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method Signature: " + methodSignature);
	
		// Display the result
		System.out.println("The result: " + result);
		
		// Caller will get the modified result.
		// The development team should be aware of AOP being used.
		for (Account account : result) {
			String name = account.getName();
			account.setName(name.toUpperCase());
		}
		
		System.out.println("---- @AfterReturning ----\n");
 	}
 	
 	@AfterThrowing(
 			pointcut = "com.example.aop.aspect.AspectExpression.afterReturningForMethodFindAccounts()",
 			throwing = "exc")
 	public void afterThrowingFindAccounts(JoinPoint joinPoint, Throwable exc) {
 		
 		System.out.println("---- @AfterThrowing ----");
 		
 		String methodName = joinPoint.getSignature().toShortString();
 		System.out.println("The method where the error occurred: " + methodName);
 		System.out.println("The exception: " + exc);
 		
 		System.out.println("---- @AfterThrowing ----\n");
 	}
 	
 	@After("com.example.aop.aspect.AspectExpression.afterReturningForMethodFindAccounts()")
 	public void afterFindAccounts(JoinPoint joinPoint) {
 		
		/*
		 * Do not use @After for tracking a result or an exception.
		 * @After cannot track the exception. The best use cases are logging and
		 * auditing.
		 */
 		
 		System.out.println("---- @After ----");
 		
 		String methodName = joinPoint.getSignature().toShortString();
 		System.out.println("====> Excecuting @After (finally) method on : " + methodName);
 		
 		System.out.println("---- @After ----\n");
 	}
 	
 	@Around("com.example.aop.aspect.AspectExpression.aroundForGetRandomNumber()")
 	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
 		
 		// @Around - pre & post-processing data, profiling code running time
 		System.out.println("\n---- @Around ----");
 		
 		long start = System.currentTimeMillis();
 		
 		// TimeUnit.SECONDS.sleep(3);
 		
 		Object result = null;
 		// Caller doesn't know that an error occured.
 		try {
 			result = proceedingJoinPoint.proceed();
 		}
 		catch (Exception e) {
 			String methodName = proceedingJoinPoint.getSignature().toShortString();
 			System.out.println("The caught error message: " + e.getMessage());
 			System.out.println("Failed to execute method '" + methodName +"'");
 			System.out.println("Return value: 0");
 			result = 0;
 		}
 		
 		long end = System.currentTimeMillis();
 		long duration = ((end-start)/1000);
 		System.out.println("Duration: " + duration);
 		
 		System.out.println("---- @Around ----\n");
 		
 		return result;
 	};
}



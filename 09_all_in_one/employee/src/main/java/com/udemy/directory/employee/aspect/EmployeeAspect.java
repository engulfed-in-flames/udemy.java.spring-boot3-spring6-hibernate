package com.udemy.directory.employee.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.udemy.directory.employee.entity.Employee;

@Aspect
@Component
public class EmployeeAspect {
	
	private Logger logger = Logger.getLogger(getClass().getName());

	@Pointcut("execution(* com.udemy.directory.employee.controller.*.*(..))")
	public void forController() {};
	
	@Pointcut("execution(* com.udemy.directory.employee.dao.*.*(..))")
	public void forDAO() {};
	
	@Pointcut("execution(* com.udemy.directory.employee.service.*.*(..))")
	public void forService() {};
	
	@Pointcut("forController() || forDAO() || forService()")
	public void forAllProcesses() {};
	
	@Before("forAllProcesses()")
	public void before(JoinPoint joinPoint) {
		
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("==== @Before " + methodName + " ====>");
		
	};
	
	@AfterReturning(
			pointcut = "forAllProcesses()",
			returning = "result"
			)
	public void afterRunning(JoinPoint joinPoint, List<Employee> result) {
		
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("==== " + methodName + " @AfterRunning ====>");
	};
}

package com.example.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/*
 * If a class has only poincut expressions (no advices),
 * @Aspect is optional
*/
@Aspect
public class AspectExpression {

	/*
	 * How can we reuse a pointcut expression? Solution : Create a pointcut
	 * declaration
	 */
	
	@Pointcut("execution(* com.example.aop.dao.*.addAccount(..))")
	public void beforeForAddAccount() {};
	
	@Pointcut("execution(* com.example.aop.dao.*.get*(..))")
	public void beforeForGetter() {};
	
	@Pointcut("execution(* com.example.aop.dao.*.set*(..))")
	public void beforeForSetter() {};
	
	@Pointcut("beforeForAddAccount() && "
	 		+ "!(beforeForGetter() || beforeForSetter())")
	public void beforeNotForGetterAndSetter() {};
	
	@Pointcut("execution(* com.example.aop.dao.AccountDAOImpl.findAccounts(..))")
	public void afterReturningForMethodFindAccounts() {};
	
	@Pointcut("execution(* com.example.aop.dao.*.getRandomNumber(..))")
	public void aroundForGetRandomNumber() {};
}

package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component @Aspect
public class MyAspect {

	@Pointcut("execution(* com.dao.impl.UserDaoImpl.*(..))")
	private void anyMethod() {}
	
	@Around("anyMethod()")
	public Object aroundMethod(ProceedingJoinPoint point)  {
		Object result=null;
		
		try {
			String methodName= point.getSignature().getName();
			System.out.println("方法"+methodName+ "秇行了 前置通知触发了");
			//这个调用,就是让被拦到目标方法执行
			result=point.proceed();
			
			System.out.println("环绕通知中的 后置通知触发了");
			
		} catch (Throwable e) {
			System.out.println("环绕通知中的后例外通知触发了");
		}finally{
			System.out.println("环绕通知中的最终通知触发了");
		}
		
		return result;
	}
	
	
}

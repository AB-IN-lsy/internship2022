package com.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;


@Component 
public class MyAspect {
	public Object aroundMethod( ProceedingJoinPoint point)  {
		Object result=null;
		
		try {
			System.out.println("环绕通知中的 前置通知触发了");
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
	

	public void beforMethod() {
		System.out.println("前置通知触发了");
	}

	public void afterMethod() {
		System.out.println("后置通知触发了");
	}

	public void finallyMethod() {
		System.out.println("最终通知触发了");
	}
	
	public void exceptionMethod() {
		System.out.println("例外通知触发了");
	} 
}

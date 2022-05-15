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
			System.out.println("����"+methodName+ "�h���� ǰ��֪ͨ������");
			//�������,�����ñ�����Ŀ�귽��ִ��
			result=point.proceed();
			
			System.out.println("����֪ͨ�е� ����֪ͨ������");
			
		} catch (Throwable e) {
			System.out.println("����֪ͨ�еĺ�����֪ͨ������");
		}finally{
			System.out.println("����֪ͨ�е�����֪ͨ������");
		}
		
		return result;
	}
	
	
}

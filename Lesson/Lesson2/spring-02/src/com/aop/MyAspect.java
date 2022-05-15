package com.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;


@Component 
public class MyAspect {
	public Object aroundMethod( ProceedingJoinPoint point)  {
		Object result=null;
		
		try {
			System.out.println("����֪ͨ�е� ǰ��֪ͨ������");
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
	

	public void beforMethod() {
		System.out.println("ǰ��֪ͨ������");
	}

	public void afterMethod() {
		System.out.println("����֪ͨ������");
	}

	public void finallyMethod() {
		System.out.println("����֪ͨ������");
	}
	
	public void exceptionMethod() {
		System.out.println("����֪ͨ������");
	} 
}

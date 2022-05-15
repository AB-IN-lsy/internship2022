package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beans.UserInfo;
import com.controller.UserServlet;

public class Test {

	public static void main(String[] args) {
		//��ʼ����������,����·���¼��������ļ� 
		ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//�������еõ�һ��bean 
		UserServlet servlet=ioc.getBean("userServlet_name",UserServlet.class );
		servlet.service();
		
		ioc.close();
	}

}

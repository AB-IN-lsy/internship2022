package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beans.UserInfo;
import com.controller.UserServlet;

public class Test {

	public static void main(String[] args) {
		//初始化容器对象,从类路径下加载配置文件 
		ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//从容器中得到一个bean 
		UserServlet servlet=ioc.getBean("userServlet_name",UserServlet.class );
		servlet.service();
		
		ioc.close();
	}

}

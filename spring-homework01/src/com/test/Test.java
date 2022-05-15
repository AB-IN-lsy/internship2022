package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.controller.TvServlet;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
	
		TvServlet tvServlet = ioc.getBean("TvServlet_name", TvServlet.class);
		tvServlet.service();
	
	
	}

}

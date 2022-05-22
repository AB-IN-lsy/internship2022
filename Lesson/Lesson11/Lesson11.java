==========================================Lesson11 =============================================
1 搭建项目框架
2 jsp模板的定义
3 搭建 springmvc 环境
4 引入静态资源
================================================================================================
==== 1 搭建项目框架
    要搭建一个 mavenweb 工程 
    
    准备工作:
        安装JDK,配置好环境变量
        安装好maven
        安装好eclipse
        安装好tomcat 
        安装好git 
        申请好gitee账号 
        
     新建一个mavenweb工程
     
     处理以下几个问题
     1) jdk版本的配置:
			   <build>
				  	<plugins>
						<plugin>
							<groupId>org.apache.maven.plugins</groupId>
							<artifactId>maven-compiler-plugin</artifactId>
							<version>3.8.1</version>
							<configuration>
								<source>1.8</source>
								<target>1.8</target>
							</configuration>
						</plugin>
				  	</plugins>	
				  </build>
				  
				  配置完以后,要更新一下maven项目,jdk的版本才能变成1.8
		
		 2) 缺少maven工程所须的几个目录
		     把jdk移除再重新添加一次
		     
		 3) 它自带的jsp上,报错,是因为依赖的sevlet,jsp相关的组件没有引入
		  	   	<!-- 添加Servlet支持 -->
		        <dependency>
		            <groupId>javax.servlet</groupId>
		            <artifactId>javax.servlet-api</artifactId>
		            <version>3.1.0</version>
		            <scope>provided</scope>
		        </dependency>
		        
		        <!-- 添加jsp支持 -->
		        <dependency>
		            <groupId>javax.servlet.jsp</groupId>
		            <artifactId>javax.servlet.jsp-api</artifactId>
		            <version>2.3.1</version>
		        </dependency>
		        
			      <!-- 添加jstl支持  -->
		        <dependency>
		            <groupId>javax.servlet</groupId>
		            <artifactId>jstl</artifactId>
		            <version>1.1.2</version>
		        </dependency>
		        <dependency>
		            <groupId>taglibs</groupId>
		            <artifactId>standard</artifactId>
		            <version>1.1.2</version>
		        </dependency>
		        
		  4) 经过上面的配置以后,jsp中的el表达式不能解析
		 		 	我们要把web.xml 改成下面的样子
					<?xml version="1.0" encoding="UTF-8"?>
					<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
						xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
						xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
					
					</web-app>
					
					附:它自带的是这样的
					<!DOCTYPE web-app PUBLIC
					 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
					 "http://java.sun.com/dtd/web-app_2_3.dtd" >
					
					<web-app>
					  <display-name>Archetype Created Web Application</display-name>
					</web-app>
					
		  5) 找到工程中的 org.eclipse.wst.common.project.facet.core.xml这个文件,进行修改
		        我的是在  D:\workspace\aolin-system\.settings 目录下
		        
						<?xml version="1.0" encoding="UTF-8"?>
						<faceted-project>
						  <fixed facet="wst.jsdt.web"/>
						  <installed facet="jst.web" version="3.1"/>   //这里,原来是2.5 ,要改成3.1
						  <installed facet="wst.jsdt.web" version="1.0"/>
						  <installed facet="java" version="1.8"/>
						</faceted-project>
						
						
			 经过上面的5步处理以后,可以在index.jsp中写上el表达式,然后发布项目,启动访问,测试一下初始环境
					<%
						request.setAttribute("msg", "init ok"); 
					%>
					
					<h1>${msg }</h1>
					
				http://localhost:8080/aolin-system/index.jsp  如果能看到el表达式解析了,证明环境准备好了
											
											
		  测试通过以后,要把项目的初始工程,上传到git上
		   
		   
==== 2 jsp模板的定义
    New Jsp File  -->next  --> 点下面的 Jsp Templates  -->右边的 New   --> Name任意输入  ,Context 选择 New JSP
    输入JSP模板的内容
    
		<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<%
		String path = request.getContextPath();   //得到项目的上下文路径,通常和工程名相同(也可以不同,自行配置,本例中得到的 /aolin-system  )
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		上面的这句相当于: 	basePath=http://localhost:8080/aolin-system/
	
		%>
		
		<!DOCTYPE HTML>
		<html>
			  <head>
			    //用来指明当前网页中,所有url的基地址  (url包扩 图片引入,css引入,js引入 ,超链接地址,表单交地址 等 )
			    //有了它以后,我们网页中的所有url 将都从这个基地址开始
			    <base href="<%=basePath%>">
			    
			    <title></title>
			    
				<meta http-equiv="pragma" content="no-cache">
				<meta http-equiv="cache-control" content="no-cache">
				<meta http-equiv="expires" content="0">    
			
				<!--
				<link rel="stylesheet" type="text/css" href="styles.css">
				-->
			
			  </head>
			  
			  <body>
			    
			  </body>
		</html>
				
		以后再创建jsp的时候,就可以使用了个模板
													 
																	  
==== 3 搭建 springmvc 环境
		 1) 配置相关依赖 	 
					<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
					  <modelVersion>4.0.0</modelVersion>
					  <groupId>com.highcom</groupId>
					  <artifactId>aolin-system</artifactId>
					  <packaging>war</packaging>
					  <version>0.0.1-SNAPSHOT</version>
					 
					  <properties>
					      <!-- 声明spring版本 -->
					  	  <spring.version>4.2.0.RELEASE</spring.version>
					  </properties>
					 
					  <dependencies>
					       <!-- 添加Servlet支持 -->
					        <dependency>
					            <groupId>javax.servlet</groupId>
					            <artifactId>javax.servlet-api</artifactId>
					            <version>3.1.0</version>
					            <scope>provided</scope>
					        </dependency>
					        
					      <!-- 添加jsp支持 -->
					        <dependency>
					            <groupId>javax.servlet.jsp</groupId>
					            <artifactId>javax.servlet.jsp-api</artifactId>
					            <version>2.3.1</version>
					        </dependency>
					        
						   <!-- 添加jstl支持  -->
					        <dependency>
					            <groupId>javax.servlet</groupId>
					            <artifactId>jstl</artifactId>
					            <version>1.1.2</version>
					        </dependency>
					        <dependency>
					            <groupId>taglibs</groupId>
					            <artifactId>standard</artifactId>
					            <version>1.1.2</version>
					        </dependency>
					        
					       <!-- 添加spring的相关支持 -->
						    <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-core</artifactId>
					            <version>${spring.version}</version> 
					        </dependency>
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-beans</artifactId>
					            <version>${spring.version}</version> 
					        </dependency>
							<dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-context</artifactId>
					            <version>${spring.version}</version>  
					        </dependency>
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-context-support</artifactId>
					            <version>${spring.version}</version>
					        </dependency>
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-web</artifactId>
					            <version>${spring.version}</version>
					        </dependency>
							<!--spring test支持-->
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-test</artifactId>
					            <version>${spring.version}</version>
					        </dependency>
					   		<!--spring mvc支持-->
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-webmvc</artifactId>
					           <version>${spring.version}</version>
					        </dependency>
					   		 <!--spring 事务管理支持-->
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-tx</artifactId>
					            <version>${spring.version}</version>
					        </dependency>
						     <!--spring jdbc操作支持-->
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-jdbc</artifactId>
					            <version>${spring.version}</version>
					        </dependency>
					   		<!--spring aop编程支持-->
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-aop</artifactId>
					            <version>${spring.version}</version>
					        </dependency>
					        <dependency>
					            <groupId>org.springframework</groupId>
					            <artifactId>spring-aspects</artifactId>
					            <version>${spring.version}</version>
					        </dependency>
							       
					  </dependencies>
					
						<build>
						  	<plugins>
								<plugin>
									<groupId>org.apache.maven.plugins</groupId>
									<artifactId>maven-compiler-plugin</artifactId>
									<version>3.8.1</version>
									<configuration>
										<source>1.8</source>
										<target>1.8</target>
									</configuration>
								</plugin>
						  	</plugins>	
						  </build>
					</project>

     2) 配置springmvc的主配置文件 
          src/main/resources/  目录里,用来放各种配置文件   先在下面建两个目录    
             mybatis/
             spring/
                   --spring-mvc.xml  //进行springmvc的配置
                   --spring-mybatis.xml //现在先不要建,将来创建
                   
	          
				  spring-mvc.xml 的内容:
					<?xml version="1.0" encoding="UTF-8"?>
					<beans xmlns="http://www.springframework.org/schema/beans"
						xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:context="http://www.springframework.org/schema/context"
						xmlns:mvc="http://www.springframework.org/schema/mvc"
						xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.2.xsd
							http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd">
					
						<!-- 开启自动扫瞄,把组件纳入spring管理 -->
						<context:component-scan base-package="com"  />
						
						<!-- 开启注解驱动 -->
						<mvc:annotation-driven />
						
						<!-- 配置视图解析器 -->
						<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
							<property name="prefix" value="/view/" />
							<property name="suffix" value=".jsp"  />
						</bean>
					</beans>
					
		 3) 配置web.xml
					<?xml version="1.0" encoding="UTF-8"?>
					<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
						xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
						xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
						
					  <!-- 初始化spring容器 -->
					  <context-param>
					  	<param-name>contextConfigLocation</param-name>
					  	<param-value>
					  		classpath:spring/spring-*.xml
					  	</param-value>
					  </context-param>
					  <listener>
					  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
					  </listener>
					
					  <!-- 配置springmvc的前端控制器 -->
					  <servlet>
						  	<servlet-name>springmvc</servlet-name>
						  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
						  	
						  	<init-param>
						  		<param-name>contextConfigLocation</param-name>
						  		<param-value>classpath:spring/spring-mvc.xml</param-value>
						  	</init-param>
						  	<load-on-startup>1</load-on-startup>
					  </servlet>
						  <servlet-mapping>
						  	<servlet-name>springmvc</servlet-name>
						  	<url-pattern>/</url-pattern>
					  </servlet-mapping>
					 
					</web-app>


     4) 控制层
					package com.controller;
					import org.springframework.stereotype.Controller;
					import org.springframework.ui.ModelMap;
					import org.springframework.web.bind.annotation.RequestMapping;
					
					@Controller
					public class TestController {
						@RequestMapping("/test")
						public String test(ModelMap m) {
							System.out.println("请求进入了控制层....");
							m.put("msg", "哈哈,springmvc初始程序大功告成");
							return "index";    //==>  /view/index.jsp
						}
					}

     5) src/main/webapp/view 目录中,创建 index.jsp ,内容  <h1>${msg}</h1>
     
     6) 发布,测试
        http://localhost:8080/aolin-system/test
     
==== 4 引入静态资源
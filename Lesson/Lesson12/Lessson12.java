=====================================Lesson12 =========================================
1 ����mybatis����
2 ���Ʋ��ҵ������
=======================================================================================
==== 1 ����mybatis����
    1) ������ص�jar
    
			  <properties>
			      <!-- ����spring�汾 -->
			  	  <spring.version>4.2.0.RELEASE</spring.version>
			  	  
			  	  <!-- ����mybais�汾 -->
			  	  <mybatis.version>3.4.0</mybatis.version>
			  </properties>
			  
			  ...
			  
		   <!-- ���mybatis֧��  -->
	  	    <dependency>
	  	   		  <groupId>org.mybatis</groupId>
	            <artifactId>mybatis</artifactId>
	            <version>${mybatis.version}</version>
	        </dependency>
	        
	         <dependency>
	  	   		  <groupId>org.mybatis</groupId>
	            <artifactId>mybatis-spring</artifactId>
	            <version>1.3.0</version>
	        </dependency>
	        
	        <!-- mysql������ -->
	         <dependency>
	  	   		  <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	            <version>8.0.18</version>
	        </dependency>
	        
	        <!-- ����Ͱ͵� druid���ӳ� -->
	         <dependency>
	  	   	  	<groupId>com.alibaba</groupId>
	            <artifactId>druid</artifactId>
	            <version>1.0.16</version>
	        </dependency>
	        
	  2) ����һ��ʵ����,�������ݿ��н�ͬ���ı�
				public class User{
				    private Integer userId;  //����id 
				    private String userName; //�˺�  
				    private String userPass;  //����
				    private String userNickname;  //�ǳ�
				    private String userEmail;  //����
				    private String userUrl;  //�û�����ַ
				    private String userAvatar;  //ָ��һ��ͼƬ��ַ(����ֶ�Ŀǰֻ�Ǳ����ֶ�,��ʱ����)
				    private String userLastLoginIp;   //����¼��IP
				    private Date userRegisterTime;   //�û�ע���ʱ��
				    private Date userLastLoginTime; //����¼��ʱ��
				    private Integer userStatus; //�û���״̬
				    private byte [] userPhoto;  //�û���Ƭ,����Ǿ������Ƭ����
				    ...
				  }
		
		 3) ����mybatis�������ļ� 
		      �ڹ��̵�  src/main/resources/ ��,�� mybatis Ŀ¼ (��ʵ�ϴξͽ�����) �����  mybatis-config.xml ����:
		      
					<?xml version="1.0" encoding="UTF-8"?>  
					<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  
					"http://mybatis.org/dtd/mybatis-3-config.dtd">  
					
					<configuration>
						  <settings>
						  	  <!-- �����Զ������������� -->
									<setting name="mapUnderscoreToCamelCase" value="true"/>
						  </settings>				    
					 </configuration>
					
				��: ����ϲ�����������ݿ��ֶε�ʱ���� user_name ,������ʵ�����ֶε�ʱ���� userName ,Ϊ�˽������Զ�ӳ��,Ҫ�����������
		
		 4) �������ݿ�������ļ� src/main/resources/db.properties
					db.driverName=com.mysql.cj.jdbc.Driver
					db.url=jdbc:mysql://localhost:3306/aolin?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
					db.username=root
					db.password=root
		
		 5) ��  src/main/resources/mybatis ��,��һ��Ŀ¼ mappings ,������ mybatis������ӳ���ļ�
		 
		 
		 6) �������ݷ��ʲ�ӿ�(�൱����ǰ��dao��)
					package com.mapper;
					import com.entity.User;
					
					/**
					 * �û����
					 */
					public interface UserMapper {
						/**
						 * �����û�������������¼
						 * @param s �û���������
						 * @return �û���Ϣ
						 */
						User loginByNameOrEmail(String s);
					}
					
			7) ������������ӿڶ�Ӧ��ӳ���ļ�  /src/main/resources/mybatis/UserMapper.xml
					<?xml version="1.0" encoding="UTF-8" ?>  
					<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
					"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
					
					<mapper namespace="com.mapper.UserMapper">
						<sql id="cols">
						    user_id, user_name, user_pass, user_nickname, user_email, user_url, user_avatar, 
						    user_last_login_ip, user_register_time, user_last_login_time, user_status
						</sql>
						
						<select id="loginByNameOrEmail" parameterType="string" resultType="User">
						    select  <include refid="cols" /> from user where user_name=#{s} or user_email=#{s} and user_status>0  limit 1	
						</select>
					</mapper>

     8) ����mybatis�� spring������
           �� src/main/resources/spring/ ��һ�� spring�� mybatis ���ϵ������ļ� spring-mybatis.xml
           
					<?xml version="1.0" encoding="UTF-8"?>
					<beans xmlns="http://www.springframework.org/schema/beans"
						xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:context="http://www.springframework.org/schema/context"
						xmlns:mvc="http://www.springframework.org/schema/mvc"
						xmlns:aop="http://www.springframework.org/schema/aop"
						xmlns:tx="http://www.springframework.org/schema/tx"
						xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.2.xsd
							http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd
							http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.1.xsd
							http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.1.xsd">
					
						<!-- �������ݿ���ص������ļ� -->
						<context:property-placeholder location="classpath:db.properties"/>	
						
						<!-- ��������Դ -->				    
						 <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
					        <property name="driverClassName" value="${db.driverName}" />
					        <property name="url" value="${db.url}" />
					        <property name="username" value="${db.username}" />
					        <property name="password" value="${db.password}" />
					     </bean>
					     
					     <!-- ����SqlSessionFactory -->
					    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
					        <!--����Դ-->
						    <property name="dataSource" ref="dataSource"/>
						    
						    <!--����mybatisȫ�������ļ�-->
						    <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"/>
						    
					        <!--mapper.xml����λ��-->
						    <property name="mapperLocations" value="classpath*:mybatis/mappings/*Mapper.xml" />
						    
					        <!--ָ����Ҫʹ�ñ�����ʵ�������ڵİ�-->
					        <property name="typeAliasesPackage" value="com.entity" />   
					    </bean>
					    
					    <!-- ����mapperɨ���� -->
					    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
					        <!--�����Ҫɨ���������м�ʹ�ð�Ƕ��Ÿ���-->
					        <property name="basePackage" value="com.mapper"></property>
					        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
					    </bean>
					    
					    <!-- �������� -->
					    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
					        <!-- ����Դ,�����������õ�druid����Դ- -->
					        <property name="dataSource" ref="dataSource"/>
					    </bean>
					    
					    <!-- ҵ������еķ�����Ҫ����������� -->
					    <aop:config>
					        <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.service.impl.*.*(..))"/>
					    </aop:config>	
					    
					    <!-- ���þ�������������� -->
					    <tx:advice id="txAdvice" transaction-manager="transactionManager">
					        <tx:attributes>
					          	<tx:method name="save*" propagation="REQUIRED"/>
					            <tx:method name="delete*" propagation="REQUIRED"/>
					            <tx:method name="update*" propagation="REQUIRED"/>
					            <tx:method name="add*" propagation="REQUIRED"/>
					            <tx:method name="find*" propagation="SUPPORTS" read-only="true"/>
					            <tx:method name="get*" propagation="SUPPORTS" read-only="true"/>
					            <tx:method name="select*" propagation="SUPPORTS" read-only="true"/>
					        </tx:attributes>
					    </tx:advice>
											
					</beans>
					
==== 2 ���Ʋ��ҵ������
       1) ҵ������
           (1) �ӿ�
							package com.service;
							import com.entity.User;
							
							/**
							 * ҵ���ӿ�
							 */
							public interface UserService {
								/**
								 * �����û����������¼
								 * @param s �˺Ż�����
								 * @return �û���Ϣ
								 */
								User loginByNameOrEmail(String s);
							}
							
					 (2) ʵ��
							package com.service.impl;
							import javax.annotation.Resource;
							import org.springframework.stereotype.Service;
							import com.entity.User;
							import com.mapper.UserMapper;
							import com.service.UserService;
							
							@Service  //ǧ��Ҫ�������ע��
							public class UserServiceImpl implements UserService {
							
								@Resource  //�������ע��,������������spring������ע����������
								private UserMapper userMapper;
								
								public User loginByNameOrEmail(String s) {
									return userMapper.loginByNameOrEmail(s);
								}	
							}

      3) ���Ʋ�
					package com.controller;
					import javax.annotation.Resource;
					import javax.servlet.http.HttpServletRequest;
					import org.springframework.stereotype.Controller;
					import org.springframework.web.bind.annotation.RequestMapping;
					import com.entity.User;
					import com.service.UserService;
					@Controller @RequestMapping("/user")
					public class UserController {
						@Resource
						UserService  userService;
						
						@RequestMapping("/login")
						public String login(HttpServletRequest request) {
							String userNameOrEmail=request.getParameter("userName");
							String userPass=request.getParameter("userPass");
							String rememberMe=request.getParameter("rememberMe");
							
							User user=userService.loginByNameOrEmail(userNameOrEmail);
							if(user==null) {
								request.setAttribute("msg", "�û�������");
								return "login";
							}
							else if(!user.getUserPass().equals(userPass)) {
								request.setAttribute("msg", "�������");
								return "login";
							}
							else {
								//�û���¼�ɹ��ܺ�,���û���ص���Ϣ�ŵ� session��,�����Ժ�ʹ��
								request.getSession().setAttribute("session_user", user);
								
								//����û���ѡ��rememberMe , ���cookie��ص���Ϣ
								//�����û�������¼ʱ��
								//�����û�������¼ip
								//user.setUserLastLoginTime(new Date());
								//user.setUserLastLoginIp(request.getRemoteAddr());
								//userService.updateUser(user);
								
								return  "index";    //src/main/webapp/view/index.jsp
							}
						}
					}
					
												        
			4) ��¼ҳ��  /view/login.jsp
				<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
				<%
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				%>
				<!DOCTYPE HTML>
				<html>
					  <head>
					    <base href="<%=basePath%>">
					    
					    <title></title>
					    
						<meta http-equiv="pragma" content="no-cache">
						<meta http-equiv="cache-control" content="no-cache">
						<meta http-equiv="expires" content="0">    
				
					  </head>
					  
					  <body>
					    <form action="user/login" method="post">
					    	<input name="userName">
					    	<input name="userPass" >
					    	<input type="submit" value="��¼">
					    </form>
					    ${msg }
					  </body>
				</html>						
				
			5) ������Ŀ,���в���,���� http://localhost:8080/aolin-system/view/login.jsp
			   �����˺Ż�����,������,���Ե�¼����
			   

==== 4 ��̬��Դ�ĵ���
    �Ѿ�̬��Դ�е� css,img,js,plugin,uploads�ȼ���Ŀ¼���Ƶ�������, src/main/webap/resources Ŀ¼�оͿ���
    �ڴ�ǰ,�Ѿ������Ŀ¼������̬��Դ��ӳ��
      spring-mvc.xml�� :
     	<mvc:resources location="/resources/" mapping="/resources/**"  />
     	
     	
     	�� �������� login.jsp    	
			<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			<%
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			%>
				       
			<!DOCTYPE html>
			<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
			<head>
				<base href="<%=basePath%>">		
			    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
			    <title>ϵͳ��¼</title>
			    <link rel="stylesheet" href="resources/plugin/font-awesome/css/font-awesome.min.css">
			    <link rel="shortcut icon" href="resources/img/logo.png">
			    <link rel='stylesheet' id='dashicons-css'  href='resources/plugin/login/dashicons.min.css' type='text/css' media='all' />
			    <link rel='stylesheet' id='buttons-css'  href='resources/plugin/login/buttons.min.css' type='text/css' media='all' />
			    <link rel='stylesheet' id='forms-css'  href='resources/plugin/login/forms.min.css' type='text/css' media='all' />
			    <link rel='stylesheet' id='l10n-css'  href='resources/plugin/login/l10n.min.css' type='text/css' media='all' />
			    <link rel='stylesheet' id='login-css'  href='resources/plugin/login/login.min.css' type='text/css' media='all' />
			    <link rel='stylesheet'  href='resources/css/login.css' type='text/css' media='all' />
			   
			    <meta name='robots' content='noindex,follow' />
			    <meta name="viewport" content="width=device-width" />
			</head>
			
			<body class="login login-action-login wp-core-ui  locale-zh-cn">
				<div id="login">
				    <br /> <br /> 
				    <form action="user/login"  method="post">
				        <p>
				            <label for="user_login">�û���������ʼ���ַ<br />
				             <input type="text" name="userName" id="user_login" class="input" value="admin" size="20" required/></label>
				        </p>
				        <p>
				            <label for="user_pass">����<br />
				                <input type="password" name="userPass" id="user_pass" class="input" value="123456" size="20" required/>
				            </label>
				        </p>
				        <p class="forgetmenot"><label for="rememberMe"><input name="rememberMe" type="checkbox" id="rememberme" value="1" checked /> ��ס����</label></p>
				        <p class="submit">
				            <input type="submit" name="wp-submit" id="submit-btn" class="button button-primary button-large" value="��¼" />
				        </p>
				    </form>
				
				    <p id="backtoblog"><a href="#">&larr; ϵͳǰ̨</a></p>
			 
				</div>
				<div class="clear"></div>
				
				 <script>
				 	 var str="${msg}"; 
				 	 
				 	 if(str!=""){
				 		 alert(str);
				 	 }
				 </script>
			</body>
			</html>

      �������� index.jsp
			<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			<%
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			%>
				   
				    
			<!DOCTYPE html>
			<html lang="zh-CN">
			<head>
			    <base href="<%=basePath%>">
			    <meta charset="utf-8">
			    <link rel="shortcut icon" href="img/logo.png">
			    <title>ϵͳ��̨��ҳ</title>       
			           
			    <link rel="stylesheet" href="resources/plugin/layui/css/layui.css">
			    <link rel="stylesheet" href="resources/css/back.css">
			    <link rel="stylesheet" href="resources/plugin/font-awesome/css/font-awesome.min.css">
			    <link rel="stylesheet" href="resources/css/index-page.css">
			
			</head>
			<body>
			<div class="layui-layout layui-layout-admin">
			    <div class="layui-header">
			        <div class="layui-logo"><a href="/admin" style="color:#009688;">
			        ��Ϣϵͳ��̨
			        </a>
			        </div>
			        <!-- ͷ�����򣨿����layui���е�ˮƽ������ -->
			        <ul class="layui-nav layui-layout-left">
			            <li class="layui-nav-item"><a href="/" target="_blank">ǰ̨</a></li>
			            <li class="layui-nav-item">
			                <a href="javascript:;">�½�</a>
			                <dl class="layui-nav-child">
			                    <dd><a href="/admin/article/insert">����</a></dd>
			                    <dd><a href="/admin/page/insert">ҳ��</a></dd>
			                    <dd><a href="/admin/category/insert">����</a></dd>
			                    <dd><a href="/admin/notice/insert">����</a></dd>
			                    <dd><a href="/admin/link/insert">����</a></dd>
			                </dl>
			            </li>
			        </ul>
			        <ul class="layui-nav layui-layout-right">
			            <li class="layui-nav-item">
			                <a href="javascript:;">
			                    <img src="resources/uploads/2017/10/20171006225356181.jpg" class="layui-nav-img">
			                    admin</a>
			                <dl class="layui-nav-child">
			                    <dd><a href="/admin/user/profile">��������</a></dd>
			                </dl>
			            </li>
			            <li class="layui-nav-item">
			                <a href="/admin/logout">�˳�</a>
			            </li>
			        </ul>
			    </div>
			
			    <div class="layui-side layui-bg-black">
			        <div class="layui-side-scroll">
			            <!-- ��ർ�����򣨿����layui���еĴ�ֱ������ -->
			            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
			                <li class="layui-nav-item layui-nav-itemed">
			                    <a class="" href="javascript:;">����</a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/article">ȫ������</a></dd>
			                        <dd><a href="/admin/article/insert">д����</a></dd>
			                        <dd><a href="/admin/category">ȫ������</a></dd>
			                        <dd><a href="/admin/tag">ȫ����ǩ</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a href="javascript:;">ҳ��</a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/page">ȫ��ҳ��</a></dd>
			                        <dd><a href="/admin/page/insert">���ҳ��</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a class="" href="javascript:;">
			                        ����
			                    </a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/link">ȫ������</a></dd>
			                        <dd><a href="/admin/link/insert">�������</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a href="javascript:;">����</a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/notice">ȫ������</a></dd>
			                        <dd><a href="/admin/notice/insert">��ӹ���</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a href="/admin/comment">
			                        ����
			                    </a>
			                </li>
			                <li class="layui-nav-item">
			                    <a class="" href="javascript:;">
			                        �û�
			                    </a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/user">ȫ���û�</a></dd>
			                        <dd><a href="/admin/user/insert">����û�</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a href="javascript:;">����</a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/menu">�˵�</a></dd>
			                        <dd><a href="/admin/options">��Ҫѡ��</a></dd>
			                    </dl>
			                </li>
			            </ul>
			        </div>
			    </div>
			
			    <div class="layui-body">
			        <!-- ������������ -->
			        <div style="padding: 15px;">
			            <!-- �������div����д������ -->
			            <div class="layui-container">
			   					 <div class="layui-row">
			        <div class="layui-col-md6">
			            <div id="dashboard_activity" class="postbox ">
			                <div class="inside">
			                    <div id="activity-widget">
			                        <div id="published-posts" class="activity-block"><h3>�������</h3> <br>
			                            <ul>
			                                <li><span>21:06 11��25��</span>
			                                        <a href="/article/33"
			                                           target="_blank">MySQL�����������</a>
			                                    </li>
			                                <li><span>21:05 11��25��</span>
			                                        <a href="/article/32"
			                                           target="_blank">Docker_���ţ�ֻҪ��ƪ�͹��ˣ������ɻ��ʺ�0����С�ף�</a>
			                                    </li>
			                                <li><span>21:02 11��25��</span>
			                                        <a href="/article/31"
			                                           target="_blank">RocketMQ ʵս֮��������</a>
			                                    </li>
			                                <li><span>21:01 11��25��</span>
			                                        <a href="/article/30"
			                                           target="_blank">SpringBoot + mongodb ����, ��¼��վ������־�����ò�ѯ����</a>
			                                    </li>
			                                <li><span>21:00 11��25��</span>
			                                        <a href="/article/29"
			                                           target="_blank">IDEA����EDAS��Ŀ</a>
			                                    </li>
			                                </ul>
			                        </div>
			                        <br>
			                        <div id="latest-comments" class="activity-block"><h3>��������</h3>
			                            <ul id="the-comment-list" data-wp-lists="list:comment">
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/88d5cb704d88bdad67d000eee4782000?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                ��<cite class="comment-author">
			                                                <a target="_blank" href=""
			                                                   rel="external nofollow"
			                                                   class="url">1111</a>
			                                            </cite>������
			                                                ��<a href="/article/6">Hibernate �򵥵�CURD����</a>��
			                                            </p>
			
			                                            <blockquote><p>1</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/29">
			                                                �ظ�
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/29">�༭</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(29)">ɾ��</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/487f87505f619bf9ea08f26bb34f8118?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                ��<cite class="comment-author">
			                                                <a target="_blank" href=""
			                                                   rel="external nofollow"
			                                                   class="url">���</a>
			                                            </cite>������
			                                                ��<a href="/article/6">Hibernate �򵥵�CURD����</a>��
			                                            </p>
			
			                                            <blockquote><p>ssss</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/22">
			                                                �ظ�
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/22">�༭</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(22)">ɾ��</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                ��<cite class="comment-author">
			                                                <a target="_blank" href=""
			                                                   rel="external nofollow"
			                                                   class="url">saysky3</a>
			                                            </cite>������
			                                                ��<a href="/article/2">springmvc ����������������</a>��
			                                            </p>
			
			                                            <blockquote><p>33333</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/20">
			                                                �ظ�
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/20">�༭</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(20)">ɾ��</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                ��<cite class="comment-author">
			                                                <a target="_blank" href=""
			                                                   rel="external nofollow"
			                                                   class="url">saysky2</a>
			                                            </cite>������
			                                                ��<a href="/article/2">springmvc ����������������</a>��
			                                            </p>
			
			                                            <blockquote><p>sssssss</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/19">
			                                                �ظ�
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/19">�༭</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(19)">ɾ��</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                ��<cite class="comment-author">
			                                                <a target="_blank" href="http://liuyanzhao.com"
			                                                   rel="external nofollow"
			                                                   class="url">saysky</a>
			                                            </cite>������
			                                                ��<a href="/article/2">springmvc ����������������</a>��
			                                            </p>
			
			                                            <blockquote><p>ssssss</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/18">
			                                                �ظ�
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/18">�༭</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(18)">ɾ��</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                </ul>
			                        </div>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="layui-col-md6">
			            <div id="dashboard_quick_press" class="postbox ">
			                <div class="inside">
			                    <form name="post" method="post" id="insertDraftForm"
			                          class="initial-form hide-if-no-js" action="/admin/article/insertDraftSubmit">
			
			                        <div class="layui-form-item">
			                            <div class="layui-input-block">
			                                <input type="text" name="articleTitle" id="articleTitle" required  lay-verify="required" placeholder="���������" autocomplete="off" class="layui-input">
			                            </div>
			                        </div>
			                        <div class="layui-form-item layui-form-text">
			                            <div class="layui-input-block">
			                                <textarea name="articleContent" placeholder="����������" id="articleContent" class="layui-textarea" required></textarea>
			                            </div>
			                        </div>
			                        <input type="hidden" name="articleStatus" value="0">
			                        <div class="layui-form-item">
			                            <div class="layui-input-block">
			                                <button class="layui-btn layui-btn-small" lay-submit lay-filter="formDemo" onclick="insertDraft()">����ݸ�</button>
			                                <button type="reset" class="layui-btn layui-btn-small layui-btn-primary">����</button>
			                            </div>
			                        </div>
			
			                    </form>
			                    <div class="drafts"><p class="view-all"><a
			                            href="/admin/article"
			                            aria-label="�鿴���вݸ�">�鿴����</a></p>
			                        <h2 class="hide-if-no-js">�ݸ�</h2>
			                        <ul>
			                            </ul>
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
						</div>
			        </div>
			
			    <div class="layui-footer">
			        <!-- �ײ��̶����� -->
			        ? ����չʾһЩ��ʾ����Ϣ <a href="#" target="_blank">��������</a> / <a href="#" target="_blank">��������</a>
			    </div>
				</div>
			
			</div>
				<script src="js/jquery.min.js"></script>
				<script src="plugin/layui/layui.all.js"></script>
				<script src="js/back.js"></script>
				
			
			</body>
			</html>
				    
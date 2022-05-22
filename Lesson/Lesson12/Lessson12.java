=====================================Lesson12 =========================================
1 配置mybatis环境
2 控制层和业务层代码
=======================================================================================
==== 1 配置mybatis环境
    1) 引入相关的jar
    
			  <properties>
			      <!-- 声明spring版本 -->
			  	  <spring.version>4.2.0.RELEASE</spring.version>
			  	  
			  	  <!-- 声明mybais版本 -->
			  	  <mybatis.version>3.4.0</mybatis.version>
			  </properties>
			  
			  ...
			  
		   <!-- 添加mybatis支持  -->
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
	        
	        <!-- mysql的驱动 -->
	         <dependency>
	  	   		  <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	            <version>8.0.18</version>
	        </dependency>
	        
	        <!-- 阿里巴巴的 druid连接池 -->
	         <dependency>
	  	   	  	<groupId>com.alibaba</groupId>
	            <artifactId>druid</artifactId>
	            <version>1.0.16</version>
	        </dependency>
	        
	  2) 声明一个实体类,并在数据库中建同样的表
				public class User{
				    private Integer userId;  //自增id 
				    private String userName; //账号  
				    private String userPass;  //密码
				    private String userNickname;  //昵称
				    private String userEmail;  //邮箱
				    private String userUrl;  //用户的网址
				    private String userAvatar;  //指向一个图片地址(这个字段目前只是保留字段,暂时不用)
				    private String userLastLoginIp;   //最后登录的IP
				    private Date userRegisterTime;   //用户注册的时间
				    private Date userLastLoginTime; //最后登录的时间
				    private Integer userStatus; //用户的状态
				    private byte [] userPhoto;  //用户照片,存的是具体的照片数据
				    ...
				  }
		
		 3) 配置mybatis的配置文件 
		      在工程的  src/main/resources/ 下,建 mybatis 目录 (其实上次就建过了) 里面放  mybatis-config.xml 内容:
		      
					<?xml version="1.0" encoding="UTF-8"?>  
					<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  
					"http://mybatis.org/dtd/mybatis-3-config.dtd">  
					
					<configuration>
						  <settings>
						  	  <!-- 开启自动峰驼命名规则 -->
									<setting name="mapUnderscoreToCamelCase" value="true"/>
						  </settings>				    
					 </configuration>
					
				附: 有人喜欢在命名数据库字段的时候用 user_name ,在命名实体类字段的时候用 userName ,为了将两者自动映射,要用上面的配置
		
		 4) 配置数据库的配置文件 src/main/resources/db.properties
					db.driverName=com.mysql.cj.jdbc.Driver
					db.url=jdbc:mysql://localhost:3306/aolin?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
					db.username=root
					db.password=root
		
		 5) 在  src/main/resources/mybatis 里,建一个目录 mappings ,用来放 mybatis的所有映射文件
		 
		 
		 6) 建立数据访问层接口(相当于以前的dao层)
					package com.mapper;
					import com.entity.User;
					
					/**
					 * 用户相关
					 */
					public interface UserMapper {
						/**
						 * 根据用户名或邮箱名登录
						 * @param s 用户名或邮箱
						 * @return 用户信息
						 */
						User loginByNameOrEmail(String s);
					}
					
			7) 建立上面这个接口对应的映射文件  /src/main/resources/mybatis/UserMapper.xml
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

     8) 配置mybatis和 spring的整合
           在 src/main/resources/spring/ 建一个 spring和 mybatis 整合的配置文件 spring-mybatis.xml
           
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
					
						<!-- 加载数据库相关的配置文件 -->
						<context:property-placeholder location="classpath:db.properties"/>	
						
						<!-- 配置数据源 -->				    
						 <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
					        <property name="driverClassName" value="${db.driverName}" />
					        <property name="url" value="${db.url}" />
					        <property name="username" value="${db.username}" />
					        <property name="password" value="${db.password}" />
					     </bean>
					     
					     <!-- 配置SqlSessionFactory -->
					    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
					        <!--数据源-->
						    <property name="dataSource" ref="dataSource"/>
						    
						    <!--加载mybatis全局配置文件-->
						    <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"/>
						    
					        <!--mapper.xml所在位置-->
						    <property name="mapperLocations" value="classpath*:mybatis/mappings/*Mapper.xml" />
						    
					        <!--指定需要使用别名的实体类所在的包-->
					        <property name="typeAliasesPackage" value="com.entity" />   
					    </bean>
					    
					    <!-- 配置mapper扫描器 -->
					    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
					        <!--如果需要扫描多个包，中间使用半角逗号隔开-->
					        <property name="basePackage" value="com.mapper"></property>
					        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
					    </bean>
					    
					    <!-- 配置事务 -->
					    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
					        <!-- 数据源,就是上面配置的druid数据源- -->
					        <property name="dataSource" ref="dataSource"/>
					    </bean>
					    
					    <!-- 业务层所有的方法都要进行事务管理 -->
					    <aop:config>
					        <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.service.impl.*.*(..))"/>
					    </aop:config>	
					    
					    <!-- 配置具体的事务管理情况 -->
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
					
==== 2 控制层和业务层代码
       1) 业务层代码
           (1) 接口
							package com.service;
							import com.entity.User;
							
							/**
							 * 业务层接口
							 */
							public interface UserService {
								/**
								 * 根据用户名或邮箱登录
								 * @param s 账号或邮箱
								 * @return 用户信息
								 */
								User loginByNameOrEmail(String s);
							}
							
					 (2) 实现
							package com.service.impl;
							import javax.annotation.Resource;
							import org.springframework.stereotype.Service;
							import com.entity.User;
							import com.mapper.UserMapper;
							import com.service.UserService;
							
							@Service  //千万不要忘了这个注解
							public class UserServiceImpl implements UserService {
							
								@Resource  //还有这个注解,它的作用是让spring帮我们注入依赖对象
								private UserMapper userMapper;
								
								public User loginByNameOrEmail(String s) {
									return userMapper.loginByNameOrEmail(s);
								}	
							}

      3) 控制层
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
								request.setAttribute("msg", "用户名错误");
								return "login";
							}
							else if(!user.getUserPass().equals(userPass)) {
								request.setAttribute("msg", "密码错误");
								return "login";
							}
							else {
								//用户登录成功能后,把用户相关的信息放到 session中,方便以后使用
								request.getSession().setAttribute("session_user", user);
								
								//如果用户勾选了rememberMe , 添加cookie相关的信息
								//更新用户的最后登录时间
								//更新用户的最后登录ip
								//user.setUserLastLoginTime(new Date());
								//user.setUserLastLoginIp(request.getRemoteAddr());
								//userService.updateUser(user);
								
								return  "index";    //src/main/webapp/view/index.jsp
							}
						}
					}
					
												        
			4) 登录页面  /view/login.jsp
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
					    	<input type="submit" value="登录">
					    </form>
					    ${msg }
					  </body>
				</html>						
				
			5) 启动项目,进行测试,访问 http://localhost:8080/aolin-system/view/login.jsp
			   输入账号或邮箱,及密码,测试登录功能
			   

==== 4 静态资源的导入
    把静态资源中的 css,img,js,plugin,uploads等几个目录复制到工程中, src/main/webap/resources 目录中就可以
    在此前,已经对这个目录做过静态资源的映射
      spring-mvc.xml中 :
     	<mvc:resources location="/resources/" mapping="/resources/**"  />
     	
     	
     	附 改造过后的 login.jsp    	
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
			    <title>系统登录</title>
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
				            <label for="user_login">用户名或电子邮件地址<br />
				             <input type="text" name="userName" id="user_login" class="input" value="admin" size="20" required/></label>
				        </p>
				        <p>
				            <label for="user_pass">密码<br />
				                <input type="password" name="userPass" id="user_pass" class="input" value="123456" size="20" required/>
				            </label>
				        </p>
				        <p class="forgetmenot"><label for="rememberMe"><input name="rememberMe" type="checkbox" id="rememberme" value="1" checked /> 记住密码</label></p>
				        <p class="submit">
				            <input type="submit" name="wp-submit" id="submit-btn" class="button button-primary button-large" value="登录" />
				        </p>
				    </form>
				
				    <p id="backtoblog"><a href="#">&larr; 系统前台</a></p>
			 
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

      改造过后的 index.jsp
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
			    <title>系统后台首页</title>       
			           
			    <link rel="stylesheet" href="resources/plugin/layui/css/layui.css">
			    <link rel="stylesheet" href="resources/css/back.css">
			    <link rel="stylesheet" href="resources/plugin/font-awesome/css/font-awesome.min.css">
			    <link rel="stylesheet" href="resources/css/index-page.css">
			
			</head>
			<body>
			<div class="layui-layout layui-layout-admin">
			    <div class="layui-header">
			        <div class="layui-logo"><a href="/admin" style="color:#009688;">
			        信息系统后台
			        </a>
			        </div>
			        <!-- 头部区域（可配合layui已有的水平导航） -->
			        <ul class="layui-nav layui-layout-left">
			            <li class="layui-nav-item"><a href="/" target="_blank">前台</a></li>
			            <li class="layui-nav-item">
			                <a href="javascript:;">新建</a>
			                <dl class="layui-nav-child">
			                    <dd><a href="/admin/article/insert">文章</a></dd>
			                    <dd><a href="/admin/page/insert">页面</a></dd>
			                    <dd><a href="/admin/category/insert">分类</a></dd>
			                    <dd><a href="/admin/notice/insert">公告</a></dd>
			                    <dd><a href="/admin/link/insert">链接</a></dd>
			                </dl>
			            </li>
			        </ul>
			        <ul class="layui-nav layui-layout-right">
			            <li class="layui-nav-item">
			                <a href="javascript:;">
			                    <img src="resources/uploads/2017/10/20171006225356181.jpg" class="layui-nav-img">
			                    admin</a>
			                <dl class="layui-nav-child">
			                    <dd><a href="/admin/user/profile">基本资料</a></dd>
			                </dl>
			            </li>
			            <li class="layui-nav-item">
			                <a href="/admin/logout">退出</a>
			            </li>
			        </ul>
			    </div>
			
			    <div class="layui-side layui-bg-black">
			        <div class="layui-side-scroll">
			            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
			                <li class="layui-nav-item layui-nav-itemed">
			                    <a class="" href="javascript:;">文章</a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/article">全部文章</a></dd>
			                        <dd><a href="/admin/article/insert">写文章</a></dd>
			                        <dd><a href="/admin/category">全部分类</a></dd>
			                        <dd><a href="/admin/tag">全部标签</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a href="javascript:;">页面</a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/page">全部页面</a></dd>
			                        <dd><a href="/admin/page/insert">添加页面</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a class="" href="javascript:;">
			                        链接
			                    </a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/link">全部链接</a></dd>
			                        <dd><a href="/admin/link/insert">添加链接</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a href="javascript:;">公告</a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/notice">全部公告</a></dd>
			                        <dd><a href="/admin/notice/insert">添加公告</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a href="/admin/comment">
			                        评论
			                    </a>
			                </li>
			                <li class="layui-nav-item">
			                    <a class="" href="javascript:;">
			                        用户
			                    </a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/user">全部用户</a></dd>
			                        <dd><a href="/admin/user/insert">添加用户</a></dd>
			                    </dl>
			                </li>
			                <li class="layui-nav-item">
			                    <a href="javascript:;">设置</a>
			                    <dl class="layui-nav-child">
			                        <dd><a href="/admin/menu">菜单</a></dd>
			                        <dd><a href="/admin/options">主要选项</a></dd>
			                    </dl>
			                </li>
			            </ul>
			        </div>
			    </div>
			
			    <div class="layui-body">
			        <!-- 内容主体区域 -->
			        <div style="padding: 15px;">
			            <!-- 下面这个div是重写的内容 -->
			            <div class="layui-container">
			   					 <div class="layui-row">
			        <div class="layui-col-md6">
			            <div id="dashboard_activity" class="postbox ">
			                <div class="inside">
			                    <div id="activity-widget">
			                        <div id="published-posts" class="activity-block"><h3>最近发布</h3> <br>
			                            <ul>
			                                <li><span>21:06 11月25日</span>
			                                        <a href="/article/33"
			                                           target="_blank">MySQL常用命令语句</a>
			                                    </li>
			                                <li><span>21:05 11月25日</span>
			                                        <a href="/article/32"
			                                           target="_blank">Docker_入门？只要这篇就够了！（纯干货适合0基础小白）</a>
			                                    </li>
			                                <li><span>21:02 11月25日</span>
			                                        <a href="/article/31"
			                                           target="_blank">RocketMQ 实战之快速入门</a>
			                                    </li>
			                                <li><span>21:01 11月25日</span>
			                                        <a href="/article/30"
			                                           target="_blank">SpringBoot + mongodb 整合, 记录网站操作日志，常用查询操作</a>
			                                    </li>
			                                <li><span>21:00 11月25日</span>
			                                        <a href="/article/29"
			                                           target="_blank">IDEA启动EDAS项目</a>
			                                    </li>
			                                </ul>
			                        </div>
			                        <br>
			                        <div id="latest-comments" class="activity-block"><h3>近期评论</h3>
			                            <ul id="the-comment-list" data-wp-lists="list:comment">
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/88d5cb704d88bdad67d000eee4782000?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                由<cite class="comment-author">
			                                                <a target="_blank" href=""
			                                                   rel="external nofollow"
			                                                   class="url">1111</a>
			                                            </cite>发表在
			                                                《<a href="/article/6">Hibernate 简单的CURD操作</a>》
			                                            </p>
			
			                                            <blockquote><p>1</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/29">
			                                                回复
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/29">编辑</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(29)">删除</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/487f87505f619bf9ea08f26bb34f8118?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                由<cite class="comment-author">
			                                                <a target="_blank" href=""
			                                                   rel="external nofollow"
			                                                   class="url">你好</a>
			                                            </cite>发表在
			                                                《<a href="/article/6">Hibernate 简单的CURD操作</a>》
			                                            </p>
			
			                                            <blockquote><p>ssss</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/22">
			                                                回复
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/22">编辑</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(22)">删除</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                由<cite class="comment-author">
			                                                <a target="_blank" href=""
			                                                   rel="external nofollow"
			                                                   class="url">saysky3</a>
			                                            </cite>发表在
			                                                《<a href="/article/2">springmvc 表单中文乱码解决方案</a>》
			                                            </p>
			
			                                            <blockquote><p>33333</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/20">
			                                                回复
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/20">编辑</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(20)">删除</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                由<cite class="comment-author">
			                                                <a target="_blank" href=""
			                                                   rel="external nofollow"
			                                                   class="url">saysky2</a>
			                                            </cite>发表在
			                                                《<a href="/article/2">springmvc 表单中文乱码解决方案</a>》
			                                            </p>
			
			                                            <blockquote><p>sssssss</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/19">
			                                                回复
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/19">编辑</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(19)">删除</a>
			                                            </span>
			                                            </p>
			                                        </div>
			                                    </li>
			                                <li class="comment   thread-even comment-item approved">
			
			                                        <img alt="" src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
			                                             class="avatar avatar-50 photo" height="50" width="50">
			                                        <div class="dashboard-comment-wrap has-row-actions">
			                                            <p class="comment-meta">
			                                                由<cite class="comment-author">
			                                                <a target="_blank" href="http://liuyanzhao.com"
			                                                   rel="external nofollow"
			                                                   class="url">saysky</a>
			                                            </cite>发表在
			                                                《<a href="/article/2">springmvc 表单中文乱码解决方案</a>》
			                                            </p>
			
			                                            <blockquote><p>ssssss</p></blockquote>
			                                            <p class="row-actions">|
			                                                <span class="">
			                                            <a data-comment-id="1268"
			                                               href="/admin/comment/reply/18">
			                                                回复
			                                            </a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="/admin/comment/edit/18">编辑</a>
			                                            </span>
			                                                <span class=""> |
			                                                <a href="javascript:void(0)"
			                                                   onclick="deleteComment(18)">删除</a>
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
			                                <input type="text" name="articleTitle" id="articleTitle" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			                            </div>
			                        </div>
			                        <div class="layui-form-item layui-form-text">
			                            <div class="layui-input-block">
			                                <textarea name="articleContent" placeholder="请输入内容" id="articleContent" class="layui-textarea" required></textarea>
			                            </div>
			                        </div>
			                        <input type="hidden" name="articleStatus" value="0">
			                        <div class="layui-form-item">
			                            <div class="layui-input-block">
			                                <button class="layui-btn layui-btn-small" lay-submit lay-filter="formDemo" onclick="insertDraft()">保存草稿</button>
			                                <button type="reset" class="layui-btn layui-btn-small layui-btn-primary">重置</button>
			                            </div>
			                        </div>
			
			                    </form>
			                    <div class="drafts"><p class="view-all"><a
			                            href="/admin/article"
			                            aria-label="查看所有草稿">查看所有</a></p>
			                        <h2 class="hide-if-no-js">草稿</h2>
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
			        <!-- 底部固定区域 -->
			        ? 这里展示一些提示性信息 <a href="#" target="_blank">其他导航</a> / <a href="#" target="_blank">其他导航</a>
			    </div>
				</div>
			
			</div>
				<script src="js/jquery.min.js"></script>
				<script src="plugin/layui/layui.all.js"></script>
				<script src="js/back.js"></script>
				
			
			</body>
			</html>
				    
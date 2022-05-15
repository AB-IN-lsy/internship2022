=======================================Lesson5 ============================================
1 简单介绍
2 配置核心控制器
3 springmvc 主配置文件
4 控制层代码
5 @RequestMapping 注解
6 控制层 中的方法的返回值
===========================================================================================
==== 1 简单介绍
    Spring MVC是当前最优秀的 MVC 框架，自从Spring 2.5 版本发布后，由于支持注解配置，易用性有了大幅度的提高。
    Spring 3.0 更加完善，实现了对Struts 2 的超越。现在越来越多的开发团队选择了Spring MVC。
	    1)Spring MVC使用简单，学习成本低。学习难度小于Struts2，Struts2用不上的多余功能太多
			2)Spring MVC很容易就可以写出性能优秀的程序，Struts2要处处小心才可以写出性能优秀的程序（指MVC部分）
			3)灵活
		
		√让我们能非常简单的设计出干净的Web层和薄薄的Web层；
		√进行更简洁的Web层的开发；
		√天生与Spring框架集成（如IoC容器、AOP等）；
		√提供强大的约定大于配置的契约式编程支持；
		√能简单的进行Web层的单元测试；
		√支持灵活的URL到页面控制器的映射；
		√非常容易与其他视图技术集成，如 Velocity、FreeMarker 等等，因为模型数据不放在特定的API里，
		  而是放在一个Model里（Map数据结构实现，因此很容易被其他框架使用）；
		√非常灵活的数据验证、格式化和数据绑定机制，能使用任何对象进行数据绑定，不必实现特定框架的API；
		√提供一套强大的JSP标签库，简化JSP开发；
		√支持灵活的本地化、主题等解析；
		√更加简单的异常处理；
		√对静态资源的支持；
		√支持Restful风格
		
==== 2 配置核心控制器
     准备 安装tomcat ,比如我的就直接解压在  D:\Program Files\apache-tomcat-9.0.56
     要注意,安装tomcat之前,要先配置好 JAVA_HOME 环境变量
     
     在命令行中进行启动测试 
     进行到它的bin目录下,执行 startup.bat
     停止tomcat 用 shutdown.bat 
     
     然后在浏览器地址栏中访问:
        http://localhost:8080/  
     能看到一个界面,证明安装成功
     
     如果启动不成功,主要的原因可能有
       1) 已经有tomcat在运行 , 会发生 端口冲突问题  Adress Already in use  ...
       2) 在tomcat 下,发布有错语的项目 
    
    
    
    1) 在eclipse中配置和tomcat的集成 
     
    2) 新建一个动态web项目 , 可以把它发布到tomcat服务器上测试一下
    
    3) 导包 
        把所用的包,放到WEB-INF 下的lib目录中就可以,注意,不用再手动添加 buildpath
    
    4) 配置核心控制器 它就是一个Servlet ,它的做用,就是把所有的请求都拦过来,交给SpringMVC框架去处理
    
    配置web.xml
			<?xml version="1.0" encoding="UTF-8"?>
			<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
			  <display-name>springmvc-01</display-name>
			  <welcome-file-list>
			    <welcome-file>login.jsp</welcome-file>
			  </welcome-file-list>
			  
			  <!-- 核心控制器,把所有的请求拦下来交给springmvc框架处理 -->
			  <servlet>
			  	<servlet-name>springmvc</servlet-name>
			  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>	
			  	<init-param>
			  		<param-name>contextConfigLocation</param-name>
			  		<param-value>classpath:springmvc-config.xml</param-value>   //配置springmvc的主配置文件的名称和路径
			  	</init-param>
			  	<load-on-startup>1</load-on-startup>  //表示在容器启动的时候,就创建这个serverlet
			  </servlet>
			  <servlet-mapping>
			  	<servlet-name>springmvc</servlet-name>
			  	<url-pattern>/</url-pattern>   //表示把所有的请求都拦下来
			  </servlet-mapping>
			</web-app>
			    
==== 3 springmvc 主配置文件
      在源文件夹下 (比如src) 建 springmvc-config.xml 
		
			<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="http://www.springframework.org/schema/beans"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:context="http://www.springframework.org/schema/context"
				xmlns:mvc="http://www.springframework.org/schema/mvc"
				xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd
					http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
					http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">
			
				<!-- 开启自动扫描 -->
				<context:component-scan base-package="com"  />
				
				<!-- 开启注解驱动 -->
				<mvc:annotation-driven /> 
				
				<!-- 视图解析器 -->
				<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
					<property name="prefix" value="/WEB-INF/view/" />
					<property name="suffix" value=".jsp"  />
				</bean>
			</beans>
			
			
==== 4 控制层代码
     1) 登录页 
        WebContent/login.jsp
			  <form action="${pageContext.request.contextPath}/login">    //${pageContext.request.contextPath} 这个写法,是得到基地址,防止路径出现问题
			  	账号:<input name="userName"  >
			  	密码:<input name="password"  >
			  	<input type="submit" value="登录">
			  </form>
			  
			   ${msg} //取出传递回来的作用域数据
			   
		2) 控制层 
				package com.controller;
				import org.springframework.stereotype.Controller;
				import org.springframework.ui.ModelMap;
				import org.springframework.web.bind.annotation.RequestMapping;
				
				@Controller
				public class UserController {
					@RequestMapping("/login")
					public String login(String userName,String password, ModelMap m) {
						if("admin".equals(userName) && "123".equals(password)) {
							 return "/main";	   //物理视图 = 前缀+逻辑视图+后缀  /WEB-INF/view/main.jsp
						}
						else {
							m.put("msg","账号或密码错误,登录失败");
							return "forward:/login.jsp";   
						}		
					}
				}
				
				
			 注意几点
			 1) 用 @RequestMapping 注解,把一个url和一个方法绑定到一起
			 2) 接收请求参数的时候,直接用方法上的参数进行接收 (要求参数名和请求中的参数名相同)
			 3) 传递作用域数据的时候,用 ModelMap 类型的对象进行传递
			 4) 在进行转向的时候, 直接 return 逻辑视图名就可以,它会自动帮我们拼接前后缀
			 
==== 5 @RequestMapping 注解
		1) 它的作用就是把url 和方法关联到一起,一个url对应一个方法 
		2) 它可以直接写在类上,写在类上,相当于为每个方法的url前面都加上类上这个url
		3) 它有六个属性  value,method,consumers,produces,params,headers 
		
		      method 属性的作用: 可以控制这个方法到底处理什么样的请求,是get还是post
		      
		      比如
					@RequestMapping(value="/login",method=RequestMethod.GET)  //这样将只处理GET请求
					如果发的是post请求,将出现下面的问题:
						HTTP状态 405 - 方法不允许		
						类型 状态报告
						消息 Request method 'POST' not supported
						
==== 6 控制层 中的方法的返回值
    1) String 类型 
         //例 
				@RequestMapping(value="/login")
					public String login(String userName,String password, ModelMap m) {
						if("admin".equals(userName) && "123".equals(password)) {
							 return "/main";	   //物理视图 = 前缀+逻辑视图+后缀  /WEB-INF/view/main.jsp
						}
						else {
							m.put("msg","账号或密码错误,登录失败");
							return "forward:/login.jsp";   
						}		
					}
					
			 String类型的返回值,代表的是逻辑视图的名称 物理视图 = 前缀+逻辑视图+后缀 
			 不过要注意,带有forward: 或  redirect:  这样的视图是不受前后缀影响的
			 
	  2) ModelAndView 类型
	      //例 用户列表查询 
	        (1) 用户信息实体类
		        package com.beans;
						public class UserInfo {
							
							public UserInfo(int id, String userName, String password, String note) {
								this.id = id;
								this.userName = userName;
								this.password = password;
								this.note = note;
							}
							
							public UserInfo() {	
							}
							
							private int id;
							private String userName;
							private String password;
							private String note;
							...get set 方法
						}
						
					(2) 模拟的dao层
							package com.dao;
							import java.util.ArrayList;
							import java.util.List;
							import com.beans.UserInfo;
							
							public class UserDao {
								private List<UserInfo> userList;
								
								{
									userList=new ArrayList<UserInfo>();
									userList.add(new UserInfo(1,"admin","123","一号用户"));
									userList.add(new UserInfo(2,"root","root","二号用户"));
									userList.add(new UserInfo(3,"sa","sa","三号用户"));
									userList.add(new UserInfo(4,"scott","scott","四号用户"));
								}
							
								public void addUser() {
								}
								
								public int deleteUser() {
									return 1;
								}
								
								public UserInfo getUserById() {
									return null;
								}
								
								public List<UserInfo> getAll(){
									return userList;
								}	
							}
							
					(3) 控制层
							package com.controller;
							import java.util.List;
							
							import org.springframework.stereotype.Controller;
							import org.springframework.ui.ModelMap;
							import org.springframework.web.bind.annotation.RequestMapping;
							import org.springframework.web.servlet.ModelAndView;
							
							import com.beans.UserInfo;
							import com.dao.UserDao;
							
							@Controller @RequestMapping("/user")
							public class UserController {
								UserDao dao=new UserDao();
								
								@RequestMapping(value="/login")
								public String login(String userName,String password, ModelMap m) {
									if("admin".equals(userName) && "123".equals(password)) {
										 return "/main";	   //物理视图 = 前缀+逻辑视图+后缀  /WEB-INF/view/main.jsp
									}
									else {
										m.put("msg","账号或密码错误,登录失败");
										return "forward:/login.jsp";   
									}		
								}	
								
								@RequestMapping("/getAll")
								public ModelAndView getAll() {
									List<UserInfo> userList= dao.getAll();		
									ModelAndView mv=new ModelAndView();
									mv.addObject("userList",userList); 
									mv.setViewName("user-manager");
									return mv;
								}
							}
							
				(4) 页面
						<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>							
						<table>
							<tr>
								<td>id</td>
								<td>账号</td>
								<td>密码</td>
								<td>备注</td>
							</tr>
							<c:forEach var="u" items="${userList }">
							<tr>
								<td>${u.id}</td>
								<td>${u.userName}</td>
								<td>${u.password}</td>
								<td>${u.note}</td>
							</tr>
							</c:forEach>	
						</table>
						 
				(5) 访问 http://localhost:8080/springmvc-01/user/getAll
														 
		3) 没有返回值 void 类型,转向的时候,可以用  request对象直接转向
		
		4) 返回json格式的数据		 
		   略					
						  
										  
												 
						
						
						    
			    
			        
    
    
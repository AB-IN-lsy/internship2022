=======================================Lesson5 ============================================
1 �򵥽���
2 ���ú��Ŀ�����
3 springmvc �������ļ�
4 ���Ʋ����
5 @RequestMapping ע��
6 ���Ʋ� �еķ����ķ���ֵ
===========================================================================================
==== 1 �򵥽���
    Spring MVC�ǵ�ǰ������� MVC ��ܣ��Դ�Spring 2.5 �汾����������֧��ע�����ã����������˴���ȵ���ߡ�
    Spring 3.0 �������ƣ�ʵ���˶�Struts 2 �ĳ�Խ������Խ��Խ��Ŀ����Ŷ�ѡ����Spring MVC��
	    1)Spring MVCʹ�ü򵥣�ѧϰ�ɱ��͡�ѧϰ�Ѷ�С��Struts2��Struts2�ò��ϵĶ��๦��̫��
			2)Spring MVC�����׾Ϳ���д����������ĳ���Struts2Ҫ����С�Ĳſ���д����������ĳ���ָMVC���֣�
			3)���
		
		���������ܷǳ��򵥵���Ƴ��ɾ���Web��ͱ�����Web�㣻
		�̽��и�����Web��Ŀ�����
		��������Spring��ܼ��ɣ���IoC������AOP�ȣ���
		���ṩǿ���Լ���������õ���Լʽ���֧�֣�
		���ܼ򵥵Ľ���Web��ĵ�Ԫ���ԣ�
		��֧������URL��ҳ���������ӳ�䣻
		�̷ǳ�������������ͼ�������ɣ��� Velocity��FreeMarker �ȵȣ���Ϊģ�����ݲ������ض���API�
		  ���Ƿ���һ��Model�Map���ݽṹʵ�֣���˺����ױ��������ʹ�ã���
		�̷ǳ�����������֤����ʽ�������ݰ󶨻��ƣ���ʹ���κζ���������ݰ󶨣�����ʵ���ض���ܵ�API��
		���ṩһ��ǿ���JSP��ǩ�⣬��JSP������
		��֧�����ı��ػ�������Ƚ�����
		�̸��Ӽ򵥵��쳣����
		�̶Ծ�̬��Դ��֧�֣�
		��֧��Restful���
		
==== 2 ���ú��Ŀ�����
     ׼�� ��װtomcat ,�����ҵľ�ֱ�ӽ�ѹ��  D:\Program Files\apache-tomcat-9.0.56
     Ҫע��,��װtomcat֮ǰ,Ҫ�����ú� JAVA_HOME ��������
     
     ���������н����������� 
     ���е�����binĿ¼��,ִ�� startup.bat
     ֹͣtomcat �� shutdown.bat 
     
     Ȼ�����������ַ���з���:
        http://localhost:8080/  
     �ܿ���һ������,֤����װ�ɹ�
     
     ����������ɹ�,��Ҫ��ԭ�������
       1) �Ѿ���tomcat������ , �ᷢ�� �˿ڳ�ͻ����  Adress Already in use  ...
       2) ��tomcat ��,�����д������Ŀ 
    
    
    
    1) ��eclipse�����ú�tomcat�ļ��� 
     
    2) �½�һ����̬web��Ŀ , ���԰���������tomcat�������ϲ���һ��
    
    3) ���� 
        �����õİ�,�ŵ�WEB-INF �µ�libĿ¼�оͿ���,ע��,�������ֶ���� buildpath
    
    4) ���ú��Ŀ����� ������һ��Servlet ,��������,���ǰ����е�����������,����SpringMVC���ȥ����
    
    ����web.xml
			<?xml version="1.0" encoding="UTF-8"?>
			<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
			  <display-name>springmvc-01</display-name>
			  <welcome-file-list>
			    <welcome-file>login.jsp</welcome-file>
			  </welcome-file-list>
			  
			  <!-- ���Ŀ�����,�����е���������������springmvc��ܴ��� -->
			  <servlet>
			  	<servlet-name>springmvc</servlet-name>
			  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>	
			  	<init-param>
			  		<param-name>contextConfigLocation</param-name>
			  		<param-value>classpath:springmvc-config.xml</param-value>   //����springmvc���������ļ������ƺ�·��
			  	</init-param>
			  	<load-on-startup>1</load-on-startup>  //��ʾ������������ʱ��,�ʹ������serverlet
			  </servlet>
			  <servlet-mapping>
			  	<servlet-name>springmvc</servlet-name>
			  	<url-pattern>/</url-pattern>   //��ʾ�����е�����������
			  </servlet-mapping>
			</web-app>
			    
==== 3 springmvc �������ļ�
      ��Դ�ļ����� (����src) �� springmvc-config.xml 
		
			<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="http://www.springframework.org/schema/beans"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:context="http://www.springframework.org/schema/context"
				xmlns:mvc="http://www.springframework.org/schema/mvc"
				xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd
					http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
					http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">
			
				<!-- �����Զ�ɨ�� -->
				<context:component-scan base-package="com"  />
				
				<!-- ����ע������ -->
				<mvc:annotation-driven /> 
				
				<!-- ��ͼ������ -->
				<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
					<property name="prefix" value="/WEB-INF/view/" />
					<property name="suffix" value=".jsp"  />
				</bean>
			</beans>
			
			
==== 4 ���Ʋ����
     1) ��¼ҳ 
        WebContent/login.jsp
			  <form action="${pageContext.request.contextPath}/login">    //${pageContext.request.contextPath} ���д��,�ǵõ�����ַ,��ֹ·����������
			  	�˺�:<input name="userName"  >
			  	����:<input name="password"  >
			  	<input type="submit" value="��¼">
			  </form>
			  
			   ${msg} //ȡ�����ݻ���������������
			   
		2) ���Ʋ� 
				package com.controller;
				import org.springframework.stereotype.Controller;
				import org.springframework.ui.ModelMap;
				import org.springframework.web.bind.annotation.RequestMapping;
				
				@Controller
				public class UserController {
					@RequestMapping("/login")
					public String login(String userName,String password, ModelMap m) {
						if("admin".equals(userName) && "123".equals(password)) {
							 return "/main";	   //������ͼ = ǰ׺+�߼���ͼ+��׺  /WEB-INF/view/main.jsp
						}
						else {
							m.put("msg","�˺Ż��������,��¼ʧ��");
							return "forward:/login.jsp";   
						}		
					}
				}
				
				
			 ע�⼸��
			 1) �� @RequestMapping ע��,��һ��url��һ�������󶨵�һ��
			 2) �������������ʱ��,ֱ���÷����ϵĲ������н��� (Ҫ��������������еĲ�������ͬ)
			 3) �������������ݵ�ʱ��,�� ModelMap ���͵Ķ�����д���
			 4) �ڽ���ת���ʱ��, ֱ�� return �߼���ͼ���Ϳ���,�����Զ�������ƴ��ǰ��׺
			 
==== 5 @RequestMapping ע��
		1) �������þ��ǰ�url �ͷ���������һ��,һ��url��Ӧһ������ 
		2) ������ֱ��д������,д������,�൱��Ϊÿ��������urlǰ�涼�����������url
		3) ������������  value,method,consumers,produces,params,headers 
		
		      method ���Ե�����: ���Կ�������������״���ʲô��������,��get����post
		      
		      ����
					@RequestMapping(value="/login",method=RequestMethod.GET)  //������ֻ����GET����
					���������post����,���������������:
						HTTP״̬ 405 - ����������		
						���� ״̬����
						��Ϣ Request method 'POST' not supported
						
==== 6 ���Ʋ� �еķ����ķ���ֵ
    1) String ���� 
         //�� 
				@RequestMapping(value="/login")
					public String login(String userName,String password, ModelMap m) {
						if("admin".equals(userName) && "123".equals(password)) {
							 return "/main";	   //������ͼ = ǰ׺+�߼���ͼ+��׺  /WEB-INF/view/main.jsp
						}
						else {
							m.put("msg","�˺Ż��������,��¼ʧ��");
							return "forward:/login.jsp";   
						}		
					}
					
			 String���͵ķ���ֵ,��������߼���ͼ������ ������ͼ = ǰ׺+�߼���ͼ+��׺ 
			 ����Ҫע��,����forward: ��  redirect:  ��������ͼ�ǲ���ǰ��׺Ӱ���
			 
	  2) ModelAndView ����
	      //�� �û��б��ѯ 
	        (1) �û���Ϣʵ����
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
							...get set ����
						}
						
					(2) ģ���dao��
							package com.dao;
							import java.util.ArrayList;
							import java.util.List;
							import com.beans.UserInfo;
							
							public class UserDao {
								private List<UserInfo> userList;
								
								{
									userList=new ArrayList<UserInfo>();
									userList.add(new UserInfo(1,"admin","123","һ���û�"));
									userList.add(new UserInfo(2,"root","root","�����û�"));
									userList.add(new UserInfo(3,"sa","sa","�����û�"));
									userList.add(new UserInfo(4,"scott","scott","�ĺ��û�"));
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
							
					(3) ���Ʋ�
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
										 return "/main";	   //������ͼ = ǰ׺+�߼���ͼ+��׺  /WEB-INF/view/main.jsp
									}
									else {
										m.put("msg","�˺Ż��������,��¼ʧ��");
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
							
				(4) ҳ��
						<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>							
						<table>
							<tr>
								<td>id</td>
								<td>�˺�</td>
								<td>����</td>
								<td>��ע</td>
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
						 
				(5) ���� http://localhost:8080/springmvc-01/user/getAll
														 
		3) û�з���ֵ void ����,ת���ʱ��,������  request����ֱ��ת��
		
		4) ����json��ʽ������		 
		   ��					
						  
										  
												 
						
						
						    
			    
			        
    
    
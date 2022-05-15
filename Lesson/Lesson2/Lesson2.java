==========================================Lesson2 ===============================================
1 �Զ�ɨ��
2 ע��װ��
3 AOP ��̵�һЩ����
4 Spring ����AOP���
5 AOP��� ����֪ͨ
6 AOP���, XML���õķ�ʽ
7 Spring JDBC 
8 ʹ��Spring �����������
9 ����XML���õķ�ʽ�����������
=================================================================================================

   ��������������:
     ��װjdk   1.8   
     
     Ȼ������һ�� JAVA_HOME 
     ������һ�� Path 
     
     ���� JAVA_HOME  ==> D:\Program Files\Java\jdk1.8.0_271
     Path ==>%JAVA_HOME%\bin;
     
     win+r ��DOS����
     
     > java -version �鿴JDK�汾 
     > set JAVA_HOME �鿴��������JAVA_HOME������
     > set Path  �鿴��������Path������
     
==== 1 �Զ�ɨ��
     �� Spring2.5 ��ʼ ������ע��ķ�ʽ,���Զ�ɨ�����ʽ��spring�е�bean����spring����
     
     1) ���� Context ���ƿռ�
	        �����Ժ�,�����ļ�����:
					<?xml version="1.0" encoding="UTF-8"?>
					<beans xmlns="http://www.springframework.org/schema/beans"
						xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:context="http://www.springframework.org/schema/context"
						xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
						
					</beans>
					
		 2) �����Զ�ɨ��
					<context:component-scan base-package="com.controller" />
					<context:component-scan base-package="com.dao.impl" />

					�����Զ�ɨ��ָ���İ����Ӱ������,����������spring�����н��й���
					��Щ������������ע��֮һ
					  @Controller  //���ڿ��Ʋ�
					  @Service   //���ڷ����
					  @Repository  //���ڳ־ò�
					  @Component  //�����������
					  
					  �û������ⲻ��
					  
					  ���Զ�ɨ�跽ʽ����spring�����bean , ��Ĭ������,Ĭ�����ƾ�����������ĸСд
					  ����Ҳ����ȷ��ָ������ 
					  @Repository("daoMysql")
					  public class UserDaoImplMySQL implements IUserDao{
					  	  ...
					  }
					  
					 ��ע�ⷽʽ�����bean Ĭ��Ҳ�ǵ�ʵ����,��������óɶ�ʵ����,Ҫ�� @Scope ע��,����
						@Repository("daoOrcle")  @Scope("prototype")
						public class UserDaoImplOracle implements IUserDao {
								...
						}
						
											 
==== 2 ע��װ��
		�����������ע������� 
			package com.controller;
			import javax.annotation.Resource;
			
			import org.springframework.stereotype.Controller;
			import com.dao.IUserDao;
			
			@Controller
			public class UserServlet {		
				@Resource(name="daoOrcle") 
				private IUserDao dao;
			
				public  void service() {
					dao.addUser();
					dao.updateUser();
					dao.deleteUser();
				}
			}
			
	
	 ˵��: 
	   1) @Resource ע�� �� javax.annotation.Resource; ��jd9�Ժ�,���������,Ҫ����������jar
	   2) @Resource ע��,Ĭ���ǰ����ƽ���װ����,�ٰ����ͽ���װ��,���ָ��������,���ϸ�����װ��
	   3) �����ͽ���װ���ʱ��,����ҵ����ƥ������,�����,��Ϊ������ȷ������װ���ĸ������,��ʱ���Ǳ����ϸ�ָ������
	   
	 ��һ��ע�� @Autowired ���Ĺ��ܺ� @Resource������
		1)���� org.springframework.beans.factory.annotation.Autowired;
		2)@Autowired	Ĭ���ǰ����ͽ���װ�� 
		3)������û�� name����,���Ҫ��ȷ��ָ������ Ҫ�� @Qualifierע�����ʹ��
				
					@Controller
					public class UserServlet {	
						@Autowired @Qualifier("daoMysql")
						private IUserDao dao;	
						public  void service() {
							dao.addUser();
							dao.updateUser();
							dao.deleteUser();
						}
					}
					
==== 3 AOP ��̵�һЩ����
	springframework -> ���ģ�AOP IOC
    AOP ����������
																									  
		== Aspect  ����
		   �����Թ�ע��ĳ���
		     
		==Joinpoint ���ӵ�
		   �����ص��ĵ�,��Spring ��,ָ���Ǳ������ķ��� 
		   
		==Pointcut �����
		   ָ������Щ���ӵ�Ҫ�������� ,�������������� get��ͷ�ķ��� ,��spring��������صı��ʽȥ����
		
		==Advice ֪ͨ 
		   ���� Joinpoint ֮��Ҫ������ , ��ǰ��֪ͨ,����֪ͨ,����֪ͨ,����֪ͨ,����֪ͨ
		
		==target Ŀ�����
		
		==Veave ֯��
		  ��  Aspect  Ӧ����Ŀ�����Ĺ���, ����֯��,�������Ͳ�����
		   
		   
==== 4 Spring ����AOP���
		Spring ��,����AOP���,��Ҫ�����ַ�ʽ		
		1) ע�ⷽʽ
		2) xml���÷�ʽ
		
	  1) ע�ⷽʽ 
	     (1) ������صİ�
	           ������maven���������Ļ�ȡ 
	      
	     (2) �����ļ�
	           ��aop���ƿռ�,Ȼ������������
	           
	           <aop:aspectj-autoproxy />
	           
	     (3) �ӿ�
						public interface IUserDao {
							void addUser();
							void updateUser();
							void deleteUser();
							UserInfo getUser(int id );
							UserInfo login(String userName,String password);
						}
						
			(4) ʵ����		
						package com.dao.impl;
						import org.springframework.stereotype.Repository;
						import com.beans.UserInfo;
						import com.dao.IUserDao;
						
						@Repository
						public class UserDaoImpl implements IUserDao{
							public void addUser() {
								System.out.println("addUser ����������  mysqlʵ��");
							}
						
							public void updateUser() {
								System.out.println(" updateUser ����������  mysqlʵ��");
							}
						
							public void deleteUser() {
								System.out.println("deleteUser ����������  mysqlʵ��");
							}
						
							public UserInfo getUser(int id) {
								System.out.println("�û���ѯ getUser �����õ���");
								if(id==1) {
									UserInfo user=new UserInfo();
									user.setId(1);
									user.setUserName("admin");
									return user;
								}
								
								return null;
							}
						
							public UserInfo login(String userName, String password) {
								System.out.println("��¼ login ����������");
								if("admin".equals(userName) &&"123".equals(password)) {
									UserInfo user=new UserInfo();
									user.setId(1);
									user.setUserName("admin");
									user.setPassword("123");
									return user;
								}
								return null;
							}
						}
						
			(5) ����һ������ 
						package com.aop;
						import org.aspectj.lang.JoinPoint;
						import org.aspectj.lang.annotation.*;
						import org.springframework.stereotype.Component;
						
						@Component @Aspect
						public class MyAspect {
							
							//���������
							@Pointcut("execution(* com.dao.impl.UserDaoImpl.*(..))")
							private void anyMethod() {}
							
							@Before("anyMethod()")
							public void beforMethod(JoinPoint point) {
								String methodName= point.getSignature().getName();
								System.out.println("����"+methodName+ "�h���� ǰ��֪ͨ������");
							}
							
							@AfterReturning("anyMethod()")
							public void afterMethod() {
								System.out.println("����֪ͨ������");
							}
							
							@After("anyMethod()")
							public void finallyMethod() {
								System.out.println("����֪ͨ������");
							}
							
							@AfterThrowing("anyMethod()")
							public void exceptionMethod() {
								System.out.println("����֪ͨ������");
							}
						}
						
		 (6) ���� 
					package com.test;
					import org.springframework.context.support.ClassPathXmlApplicationContext;
					
					import com.controller.UserServlet;
					import com.dao.IUserDao;
					import com.dao.impl.UserDaoImpl;
					
					public class Test {
					
						public static void main(String[] args) {
							ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("beans.xml");
							
							IUserDao dao=ioc.getBean("userDaoImpl",IUserDao.class);
							
							dao.getUser(2);
							
							dao.addUser();
						
							dao.deleteUser();
						}
					
					}
					
					���Կ���,ÿ��������ִ��,�������˶�Ӧ��֪ͨ, �������ִ���г����쳣,����������֪ͨ
					
			
			����������Ӧ�ı��ʽ
			   @Pointcut("execution(* com.dao.impl.UserDaoImpl.*(..))")
			   
			   execution() //��ʾ��������ִ�еķ���
			   *  //��ʾ������������ķ���ֵ��ʲô����
			   
				 com.dao.impl.UserDaoImpl  //��ʾҪ���ص���
				 .*  //��ʾҪ�������еķ���
				 (..) //��ʾ���ܷ����Ĳ����м���,Ҳ������ʲô����
				 
==== 5 AOP��� ����֪ͨ
		
		@Component @Aspect
		public class MyAspect {
			//���������
			@Pointcut("execution(* com.dao.impl.UserDaoImpl.*(..))")
			private void anyMethod() {}
			
			@Around("anyMethod()")
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
	
			//����
			public static void main(String[] args) {
				ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("beans.xml");
				IUserDao dao=ioc.getBean("userDaoImpl",IUserDao.class);
				UserInfo user=dao.getUser(9);
				System.out.println(user);
			}
  
     ���Կ���,�����ַ�ʽ���ǿ���
     1) �õ�����������,��Ӧ�ĵ�����,�����Ĳ����������Ϣ��  ProceedingJoinPoint point ��������оͿ���
     2) ���Կ��Ʒ����Ƿ�ִ�� 
          point.proceed();  �����������,��ִ��,������,�Ͳ�ִ��
     
     3) ���Կ��Ʒ����ķ��ؽ��

==== 6 AOP���, XML���õķ�ʽ
		�����XML���õķ�ʽ,�Ͳ����ټӸ���ע����
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
				
				�����ļ�:
					<aop:config>
						<aop:aspect ref="myAspect">
							
							<aop:pointcut expression="execution(* com.dao.impl.UserDaoImpl.*(..))" id="pointCutXXX"/>
							<!--  
							<aop:before method="beforMethod"  pointcut-ref="pointCutXXX"/>
							<aop:after-returning method="afterMethod"   pointcut-ref="pointCutXXX" />
							<aop:after method="finallyMethod" pointcut-ref="pointCutXXX" />
							<aop:after-throwing method="exceptionMethod" pointcut-ref="pointCutXXX"  />
							-->
							<aop:around method="aroundMethod"  pointcut-ref="pointCutXXX" />
						</aop:aspect>
					</aop:config>
					
					
==== 7 Spring JDBC 

     1) �����ļ��н�����������Դ
					<?xml version="1.0" encoding="UTF-8"?>
					<beans xmlns="http://www.springframework.org/schema/beans"
						xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:context="http://www.springframework.org/schema/context"
						xmlns:aop="http://www.springframework.org/schema/aop"
						xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
							http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd">
							
							<context:component-scan base-package="com" />
							
							<bean name="dataSource"  class="org.springframework.jdbc.datasource.DriverManagerDataSource">
								<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"  />
								<property name="url" value="jdbc:mysql://localhost:3306/oa?useUnicode=true&amp;characterEncoding=UTF8&amp;serverTimezone=UTC&amp;useSSL=false&amp;allowPublicKeyRetrieval=true" />
								<property name="username" value="root" />
								<property name="password" value="root" />
							</bean>
				 </beans>

			     
		2) ����mysql��������,����ӵ�����·����
	      	mysql-connector-java-8.0.12.jar
	      	
	      	
	  3) dao��
					package com.dao.impl;
					import javax.annotation.Resource;
					import javax.sql.DataSource;
					import org.springframework.jdbc.core.BeanPropertyRowMapper;
					import org.springframework.jdbc.core.JdbcTemplate;
					import org.springframework.stereotype.Repository;
					import com.beans.UserInfo;
					
					@Repository
					public class UserDaoImpl {
						private JdbcTemplate t;   //�����,��Spring�ṩ��,һ�����������ݿ��������
						
						@Resource  //��spring�����е�ʱ������ǰ� dataSourceע����
						public void SetDataSoruce(DataSource dataSource) {
							t=new JdbcTemplate(dataSource);
						}
						
						//����û�
						public int addUser(UserInfo user) {
							String sql="insert into userInfo (userName,password,note) values (?,?,?) ";
							return t.update(sql, user.getUserName(),user.getPassword(),user.getNote());
						}
						
						//ɾ���û�
						public int deleteUserById(int id){
							return t.update("delete from userInfo where id =? ",id);
						}
						
						//�޸��û�
						public int updateUser(UserInfo user) {
							String sql="update userInfo set userName=?, password=?,note=? where id =?" ;
							Object [] params= {
								user.getUserName(),
								user.getPassword(),
								user.getNote(),
								user.getId()
							};
							return t.update(sql, params);
						}
						
						//����id��ѯ�û�
						public UserInfo getUserById(int id) {
							String sql="select * from userInfo where id=?";
							return t.queryForObject(sql,  new BeanPropertyRowMapper<UserInfo>(UserInfo.class),id);	
						}
					}
					
		4) ����
				package com.test;
				import org.springframework.context.support.ClassPathXmlApplicationContext;
				import com.beans.UserInfo;
				import com.dao.impl.UserDaoImpl;
				
				public class Test {
					public static void main(String[] args) {
						ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("beans.xml");
						UserDaoImpl dao=ioc.getBean("userDaoImpl",UserDaoImpl.class);
						
						/* ����û�
						UserInfo user=new UserInfo();
						user.setUserName("����");
						user.setPassword("bzh");
						user.setNote("�й���������");
						
						int result=dao.addUser(user);
						System.out.println(result==1?"��ӳɹ�":"���ʧ��");  */
						
						/*��ѯ�û�
						UserInfo user=dao.getUserById(5);
						System.out.println(user);
						
						user.setUserName("������");
						user.setPassword("123");
						user.setNote("��װ��Ȼ������,���й���Ȼ������");
						int result=dao.updateUser(user);
						System.out.println(result==1?"����":"����ʧ��"); */
						 
						//ɾ��
						int result=dao.deleteUserById(6); 
						System.out.println(result==1?"ɾ���ɹ�":"ɾ��ʧ��");
						
					}
				
				}
			
									      	
==== 8 ʹ��Spring �����������
   1) ע�ⷽʽ
   2) XML ���õķ�ʽ
   
   
    1) ע�ⷽʽ���� (ע��,Ҫ�� tx���ƿռ�)
		   (1)
				<!-- ������������� -->
				<bean name="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
					<property name="dataSource" ref="dataSource" />
				</bean>
				
				<!-- ����ע������ -->
				<tx:annotation-driven transaction-manager="txManager" />
				
	     (2) �� Ҫ�����������ķ��������ϼ�ע��
						@Transactional
						public void replace() {
							UserInfo user=new UserInfo();
							user.setUserName("��С��");
							user.setPassword("1111");
							user.setNote("����Ժ������");
							
							addUser(user);
							int a=9/3; 
							deleteUserById(5);
						}
								 
					 ���г���,���ǿ��Է���,���ڿ��������� 
					 ��������ķ���,Ҫô��ȫִ�гɹ�,Ҫôȫʧ��  
					 
					 
				  Transactional ���ע��Ҳ���Է���������,��ʾ���������еķ�����֧������
				  ��һ�������, ��ѯ��ķ������Բ��ÿ�������֧�� ,������������������
					@Transactional(propagation = Propagation.NOT_SUPPORTED)
					public UserInfo getUserById(int id) {
						String sql="select * from userInfo where id=?";
						return t.queryForObject(sql,  new BeanPropertyRowMapper<UserInfo>(UserInfo.class),id);	
					}
								
==== 9 ����XML���õķ�ʽ�����������  	
    ������������ķ�ʽ����,����������ÿ���ȥ����
      	<!-- ����ע������ 
		        <tx:annotation-driven transaction-manager="txManager" />
		    -->
									 		
		�ܵ������ļ�
		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns:context="http://www.springframework.org/schema/context"
			xmlns:aop="http://www.springframework.org/schema/aop"
			xmlns:tx="http://www.springframework.org/schema/tx"
			xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
				http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
				http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
				http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
				
				<context:component-scan base-package="com" />
				
				<bean name="dataSource"  class="org.springframework.jdbc.datasource.DriverManagerDataSource">
					<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"  />
					<property name="url" value="jdbc:mysql://localhost:3306/oa?useUnicode=true&amp;characterEncoding=UTF8&amp;serverTimezone=UTC&amp;useSSL=false&amp;allowPublicKeyRetrieval=true" />
					<property name="username" value="root" />
					<property name="password" value="root" />
				</bean>
				
				<!-- ������������� -->
				<bean name="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
					<property name="dataSource" ref="dataSource" />
				</bean>
				
				<aop:config>
					<aop:pointcut expression="execution(* com.dao.impl.UserDaoImpl.*(..))" id="txPonitCut"/>
					<aop:advisor advice-ref="txAdvice"  pointcut-ref="txPonitCut"/>
				</aop:config>
				
				<tx:advice id="txAdvice" transaction-manager="txManager">   
					<tx:attributes>
						<tx:method name="get*"  read-only="false" propagation="NOT_SUPPORTED"   />
						<tx:method name="*"  />
					</tx:attributes>
				</tx:advice>	
			
		</beans>
		
		
		�����õ�dao�㷽�� 

			public void replace() {
				UserInfo user=new UserInfo();
				user.setUserName("С�׳�");
				user.setPassword("222555000");
				user.setNote("�׹���ͳ");
				
				addUser(user);
				
				int a=9/3; 
				
				deleteUserById(2);
			}
			

																	    
												    
					    
					    
					    
					    
					    
					    
					    
	    
	    
	    
	    
	    
	    
	    
		   
		   
		   
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     										    
																			
			
		
		
		
		
		
		
		
		============	��: Spring execution ���ʽ ===========================
   ��   @Pointcut("execution(* cat.dao.impl.UserDaoImpl.add*(..))||"   ��������add��ͷ�ķ���
							+ "execution(* cat.dao.impl.UserDaoImpl.upd*(..))||"  �������е�upd��ͷ�ķ���
							+ "execution(* cat.dao.impl.UserDaoImpl.del*(..))" 
										
		execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
		���˷�������ģʽ���������Ƭ���е�ret-type-pattern��������ģʽ�Ͳ���ģʽ���⣬���еĲ��ֶ��ǿ�ѡ�ġ� ��������ģʽ�����˷����ķ������ͱ�������ƥ��һ�����ӵ㡣 ���ʹ�õ���Ƶ���ķ�������ģʽ�� *����������ƥ������ķ������͡� һ��ȫ���޶�����������ֻ��ƥ�䷵�ظ������͵ķ���������ģʽƥ����Ƿ������� �����ʹ�� * ͨ�����Ϊ���л��߲�������ģʽ�� ����ģʽ��΢�е㸴�ӣ�() ƥ����һ���������κβ����ķ����� �� (..) ƥ����һ�������������������ķ���������߸��ࣩ�� ģʽ (*) ƥ����һ������һ���κ����͵Ĳ����ķ����� ģʽ (*,String) ƥ����һ���������������ķ�������һ���������������ͣ��ڶ����������String���͡�
		
		�������һЩ�����������ʽ�����ӡ�
		
		���⹫��������ִ�У�
		execution(public * *(..)) 
		�κ�һ���ԡ�set����ʼ�ķ�����ִ�У�
		execution(* set*(..)) 
		AccountService �ӿڵ����ⷽ����ִ�У�
		execution(* com.xyz.service.AccountService.*(..)) 
		������service��������ⷽ����ִ�У�
		execution(* com.xyz.service.*.*(..)) 
		������service�������Ӱ�������ⷽ����ִ�У�
		execution(* com.xyz.service..*.*(..)) 
		��service������������ӵ㣨��Spring AOP��ֻ�Ƿ���ִ�У� ��
		within(com.xyz.service.*) 
		��service�������Ӱ�����������ӵ㣨��Spring AOP��ֻ�Ƿ���ִ�У� ��
		within(com.xyz.service..*) 
		ʵ���� AccountService �ӿڵĴ��������������ӵ㣨��Spring AOP��ֻ�Ƿ���ִ�У� ��
		this(com.xyz.service.AccountService)
		'this'��binding form���õĸ��ࣺ- �볣����������֪ͨ���½��й������ʹ�ô�����������֪ͨ���ڷ��ʵ��Ĳ��֡� 
		ʵ���� AccountService �ӿڵ�Ŀ�������������ӵ㣨��Spring AOP��ֻ�Ƿ���ִ�У� ��
		target(com.xyz.service.AccountService)
		'target'��binding form���õĸ��ࣺ- �볣����������֪ͨ���½��й������ʹ��Ŀ����������֪ͨ���ڷ��ʵ��Ĳ��֡� 
		�κ�һ��ֻ����һ����������������ʱ����Ĳ���ʵ���� Serializable �ӿڵ����ӵ� ����Spring AOP��ֻ�Ƿ���ִ�У� 
		args(java.io.Serializable)
		'args'��binding form���õĸ��ࣺ- �볣����������֪ͨ���½��й������ʹ�÷�������������֪ͨ���ڷ��ʵ��Ĳ��֡� ��ע���������и���������㲻ͬ�� execution(* *(java.io.Serializable))�� argsֻ���ڶ�̬����ʱ��������ǿ����л��ģ�Serializable����ƥ�䣬��execution �ڴ��������ǩ������������ʵ���� Serializable �ӿ�ʱ��ƥ�䡣 
		��һ�� @Transactional ע���Ŀ������е��������ӵ㣨��Spring AOP��ֻ�Ƿ���ִ�У� 
		@target(org.springframework.transaction.annotation.Transactional)
		'@target' Ҳ������binding form��ʹ�ã��볣����������֪ͨ���½��й������ʹ��annotation���������֪ͨ���ڷ��ʵ��Ĳ��֡� 
		�κ�һ��Ŀ�����������������һ�� @Transactional ע������ӵ㣨��Spring AOP��ֻ�Ƿ���ִ�У�
		@within(org.springframework.transaction.annotation.Transactional)
		'@within'Ҳ������binding form��ʹ�ã�- �볣����������֪ͨ���½��й������ʹ��annotation���������֪ͨ���ڷ��ʵ��Ĳ��֡� 
		�κ�һ��ִ�еķ�����һ�� @Transactional annotation�����ӵ㣨��Spring AOP��ֻ�Ƿ���ִ�У� 
		@annotation(org.springframework.transaction.annotation.Transactional)
		'@annotation' Ҳ������binding form��ʹ�ã�- �볣����������֪ͨ���½��й������ʹ��annotation���������֪ͨ���ڷ��ʵ��Ĳ��֡� 
		�κ�һ������һ�����������Ҵ���Ĳ���������ʱ������ʵ���� @Classified annotation�����ӵ㣨��Spring AOP��ֻ�Ƿ���ִ�У�
				 				 
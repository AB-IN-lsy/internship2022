==========================================Lesson2 ===============================================
1 自动扫描
2 注解装配
3 AOP 编程的一些概念
4 Spring 进行AOP编程
5 AOP编程 环绕通知
6 AOP编程, XML配置的方式
7 Spring JDBC 
8 使用Spring 进行事务管理
9 基于XML配置的方式进行事务管理
=================================================================================================

   环境变量的配置:
     安装jdk   1.8   
     
     然后配置一下 JAVA_HOME 
     再配置一下 Path 
     
     比如 JAVA_HOME  ==> D:\Program Files\Java\jdk1.8.0_271
     Path ==>%JAVA_HOME%\bin;
     
     win+r 开DOS窗口
     
     > java -version 查看JDK版本 
     > set JAVA_HOME 查看环境变量JAVA_HOME的配置
     > set Path  查看环境变量Path的配置
     
==== 1 自动扫描
     从 Spring2.5 开始 可以用注解的方式,以自动扫描的形式把spring中的bean纳入spring管理
     
     1) 引入 Context 名称空间
	        引入以后,配置文件如下:
					<?xml version="1.0" encoding="UTF-8"?>
					<beans xmlns="http://www.springframework.org/schema/beans"
						xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:context="http://www.springframework.org/schema/context"
						xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
						
					</beans>
					
		 2) 开启自动扫描
					<context:component-scan base-package="com.controller" />
					<context:component-scan base-package="com.dao.impl" />

					它会自动扫描指定的包和子包里的类,把它们纳入spring容器中进行管理
					这些类必须带有以下注解之一
					  @Controller  //用于控制层
					  @Service   //用于服务层
					  @Repository  //用于持久层
					  @Component  //用于其他组件
					  
					  用混了问题不大
					  
					  以自动扫描方式纳入spring管理的bean , 有默认名称,默认名称就是类名首字母小写
					  我们也可明确的指定名称 
					  @Repository("daoMysql")
					  public class UserDaoImplMySQL implements IUserDao{
					  	  ...
					  }
					  
					 用注解方式管理的bean 默认也是单实例的,如果想配置成多实例的,要用 @Scope 注解,如下
						@Repository("daoOrcle")  @Scope("prototype")
						public class UserDaoImplOracle implements IUserDao {
								...
						}
						
											 
==== 2 注解装配
		解决的是依赖注入的问题 
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
			
	
	 说明: 
	   1) @Resource 注解 是 javax.annotation.Resource; 在jd9以后,如果想用它,要导入其他的jar
	   2) @Resource 注解,默认是按名称进行装配置,再按类型进行装配,如果指定了名称,则严格按名称装配
	   3) 按类型进行装配的时候,如果找到多个匹配类型,会出错,因为它不能确定到底装配哪个类对象,这时我们必须严格指定名称
	   
	 另一个注解 @Autowired 它的功能和 @Resource很相似
		1)它是 org.springframework.beans.factory.annotation.Autowired;
		2)@Autowired	默认是按类型进行装配 
		3)它本身没有 name属性,如果要明确的指明类型 要和 @Qualifier注解配合使用
				
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
					
==== 3 AOP 编程的一些概念
	springframework -> 核心：AOP IOC
    AOP 面象切面编程
																									  
		== Aspect  切面
		   横切性关注点的抽象
		     
		==Joinpoint 连接点
		   被拦截到的点,在Spring 中,指的是被拦到的方法 
		   
		==Pointcut 切入点
		   指的是哪些连接点要进行拦截 ,比如拦截所有以 get开头的方法 ,在spring可以用相关的表达式去定义
		
		==Advice 通知 
		   拦到 Joinpoint 之后要做的事 , 有前置通知,后置通知,最终通知,例外通知,环绕通知
		
		==target 目标对象
		
		==Veave 织入
		  将  Aspect  应用于目标对象的过程, 经过织入,代理对象就产生了
		   
		   
==== 4 Spring 进行AOP编程
		Spring 中,进行AOP编程,主要有两种方式		
		1) 注解方式
		2) xml配置方式
		
	  1) 注解方式 
	     (1) 导入相关的包
	           可以用maven进行依赖的获取 
	      
	     (2) 配置文件
	           打开aop名称空间,然后做如下配置
	           
	           <aop:aspectj-autoproxy />
	           
	     (3) 接口
						public interface IUserDao {
							void addUser();
							void updateUser();
							void deleteUser();
							UserInfo getUser(int id );
							UserInfo login(String userName,String password);
						}
						
			(4) 实现类		
						package com.dao.impl;
						import org.springframework.stereotype.Repository;
						import com.beans.UserInfo;
						import com.dao.IUserDao;
						
						@Repository
						public class UserDaoImpl implements IUserDao{
							public void addUser() {
								System.out.println("addUser 方法调用了  mysql实现");
							}
						
							public void updateUser() {
								System.out.println(" updateUser 方法调用了  mysql实现");
							}
						
							public void deleteUser() {
								System.out.println("deleteUser 方法调用了  mysql实现");
							}
						
							public UserInfo getUser(int id) {
								System.out.println("用户查询 getUser 方法得到了");
								if(id==1) {
									UserInfo user=new UserInfo();
									user.setId(1);
									user.setUserName("admin");
									return user;
								}
								
								return null;
							}
						
							public UserInfo login(String userName, String password) {
								System.out.println("登录 login 方法调用了");
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
						
			(5) 声明一个切面 
						package com.aop;
						import org.aspectj.lang.JoinPoint;
						import org.aspectj.lang.annotation.*;
						import org.springframework.stereotype.Component;
						
						@Component @Aspect
						public class MyAspect {
							
							//定义切入点
							@Pointcut("execution(* com.dao.impl.UserDaoImpl.*(..))")
							private void anyMethod() {}
							
							@Before("anyMethod()")
							public void beforMethod(JoinPoint point) {
								String methodName= point.getSignature().getName();
								System.out.println("方法"+methodName+ "秇行了 前置通知触发了");
							}
							
							@AfterReturning("anyMethod()")
							public void afterMethod() {
								System.out.println("后置通知触发了");
							}
							
							@After("anyMethod()")
							public void finallyMethod() {
								System.out.println("最终通知触发了");
							}
							
							@AfterThrowing("anyMethod()")
							public void exceptionMethod() {
								System.out.println("例外通知触发了");
							}
						}
						
		 (6) 测试 
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
					
					可以看到,每个方法的执行,都触发了对应的通知, 如果方法执行中出现异常,将触发例外通知
					
			
			关于切入点对应的表达式
			   @Pointcut("execution(* com.dao.impl.UserDaoImpl.*(..))")
			   
			   execution() //表示拦截正在执行的方法
			   *  //表示不管这个方法的返回值是什么类型
			   
				 com.dao.impl.UserDaoImpl  //表示要拦截的类
				 .*  //表示要拦截所有的方法
				 (..) //表示不管方法的参数有几个,也不管是什么类型
				 
==== 5 AOP编程 环绕通知
		
		@Component @Aspect
		public class MyAspect {
			//定义切入点
			@Pointcut("execution(* com.dao.impl.UserDaoImpl.*(..))")
			private void anyMethod() {}
			
			@Around("anyMethod()")
			public Object aroundMethod( ProceedingJoinPoint point)  {
				Object result=null;
				
				try {
					System.out.println("环绕通知中的 前置通知触发了");
					//这个调用,就是让被拦到目标方法执行
					result=point.proceed();
					
					System.out.println("环绕通知中的 后置通知触发了");
					
				} catch (Throwable e) {
					System.out.println("环绕通知中的后例外通知触发了");
				}finally{
					System.out.println("环绕通知中的最终通知触发了");
				}
				
				return result;
			}
	
			//测试
			public static void main(String[] args) {
				ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("beans.xml");
				IUserDao dao=ioc.getBean("userDaoImpl",IUserDao.class);
				UserInfo user=dao.getUser(9);
				System.out.println(user);
			}
  
     可以看到,用这种方式我们可以
     1) 得到方法的名称,对应的调用者,方法的参数等相关信息从  ProceedingJoinPoint point 这个参数中就可以
     2) 可以控制方法是否执行 
          point.proceed();  调用这个方法,就执行,不调用,就不执行
     
     3) 可以控制方法的返回结果

==== 6 AOP编程, XML配置的方式
		如果用XML配置的方式,就不用再加各种注解了
				package com.aop;
				import org.aspectj.lang.ProceedingJoinPoint;
				import org.springframework.stereotype.Component;
					
				@Component 
				public class MyAspect {
					public Object aroundMethod( ProceedingJoinPoint point)  {
						Object result=null;
						
						try {
							System.out.println("环绕通知中的 前置通知触发了");
							//这个调用,就是让被拦到目标方法执行
							result=point.proceed();
							
							System.out.println("环绕通知中的 后置通知触发了");
							
						} catch (Throwable e) {
							System.out.println("环绕通知中的后例外通知触发了");
						}finally{
							System.out.println("环绕通知中的最终通知触发了");
						}
						
						return result;
					}
					
				
					public void beforMethod() {
						System.out.println("前置通知触发了");
					}
				
					public void afterMethod() {
						System.out.println("后置通知触发了");
					}
				
					public void finallyMethod() {
						System.out.println("最终通知触发了");
					}
					
					public void exceptionMethod() {
						System.out.println("例外通知触发了");
					} 
				}
				
				配置文件:
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

     1) 配置文件中进行配置数据源
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

			     
		2) 导入mysql的驱动包,并添加到构建路径中
	      	mysql-connector-java-8.0.12.jar
	      	
	      	
	  3) dao层
					package com.dao.impl;
					import javax.annotation.Resource;
					import javax.sql.DataSource;
					import org.springframework.jdbc.core.BeanPropertyRowMapper;
					import org.springframework.jdbc.core.JdbcTemplate;
					import org.springframework.stereotype.Repository;
					import com.beans.UserInfo;
					
					@Repository
					public class UserDaoImpl {
						private JdbcTemplate t;   //这个类,是Spring提供的,一个传用于数据库操作的类
						
						@Resource  //让spring在运行的时候帮我们把 dataSource注起来
						public void SetDataSoruce(DataSource dataSource) {
							t=new JdbcTemplate(dataSource);
						}
						
						//添加用户
						public int addUser(UserInfo user) {
							String sql="insert into userInfo (userName,password,note) values (?,?,?) ";
							return t.update(sql, user.getUserName(),user.getPassword(),user.getNote());
						}
						
						//删除用户
						public int deleteUserById(int id){
							return t.update("delete from userInfo where id =? ",id);
						}
						
						//修改用户
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
						
						//根据id查询用户
						public UserInfo getUserById(int id) {
							String sql="select * from userInfo where id=?";
							return t.queryForObject(sql,  new BeanPropertyRowMapper<UserInfo>(UserInfo.class),id);	
						}
					}
					
		4) 测试
				package com.test;
				import org.springframework.context.support.ClassPathXmlApplicationContext;
				import com.beans.UserInfo;
				import com.dao.impl.UserDaoImpl;
				
				public class Test {
					public static void main(String[] args) {
						ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("beans.xml");
						UserDaoImpl dao=ioc.getBean("userDaoImpl",UserDaoImpl.class);
						
						/* 添加用户
						UserInfo user=new UserInfo();
						user.setUserName("拜振华");
						user.setPassword("bzh");
						user.setNote("中国人民万岁");
						
						int result=dao.addUser(user);
						System.out.println(result==1?"添加成功":"添加失败");  */
						
						/*查询用户
						UserInfo user=dao.getUserById(5);
						System.out.println(user);
						
						user.setUserName("川建国");
						user.setPassword("123");
						user.setNote("洋装虽然穿在身,但中国依然在我心");
						int result=dao.updateUser(user);
						System.out.println(result==1?"更新":"更新失败"); */
						 
						//删除
						int result=dao.deleteUserById(6); 
						System.out.println(result==1?"删除成功":"删除失败");
						
					}
				
				}
			
									      	
==== 8 使用Spring 进行事务管理
   1) 注解方式
   2) XML 配置的方式
   
   
    1) 注解方式配置 (注意,要打开 tx名称空间)
		   (1)
				<!-- 配置事务管理器 -->
				<bean name="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
					<property name="dataSource" ref="dataSource" />
				</bean>
				
				<!-- 开启注解驱动 -->
				<tx:annotation-driven transaction-manager="txManager" />
				
	     (2) 在 要进行事务管理的方法或类上加注解
						@Transactional
						public void replace() {
							UserInfo user=new UserInfo();
							user.setUserName("白小吃");
							user.setPassword("1111");
							user.setNote("精神病院出来的");
							
							addUser(user);
							int a=9/3; 
							deleteUserById(5);
						}
								 
					 运行程序,我们可以发现,则于开启了事务 
					 所以上面的方法,要么完全执行成功,要么全失败  
					 
					 
				  Transactional 这个注解也可以放在类体上,表示类体中所有的方法都支持事务
				  但一般情况下, 查询类的方法可以不用开启事务支持 ,可以象下面这样处理
					@Transactional(propagation = Propagation.NOT_SUPPORTED)
					public UserInfo getUserById(int id) {
						String sql="select * from userInfo where id=?";
						return t.queryForObject(sql,  new BeanPropertyRowMapper<UserInfo>(UserInfo.class),id);	
					}
								
==== 9 基于XML配置的方式进行事务管理  	
    如果采用这样的方式配置,则下面的配置可以去掉了
      	<!-- 开启注解驱动 
		        <tx:annotation-driven transaction-manager="txManager" />
		    -->
									 		
		总的配置文件
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
				
				<!-- 配置事务管理器 -->
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
		
		
		测试用的dao层方法 

			public void replace() {
				UserInfo user=new UserInfo();
				user.setUserName("小白吃");
				user.setPassword("222555000");
				user.setNote("米国总统");
				
				addUser(user);
				
				int a=9/3; 
				
				deleteUserById(2);
			}
			

																	    
												    
					    
					    
					    
					    
					    
					    
					    
	    
	    
	    
	    
	    
	    
	    
		   
		   
		   
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     										    
																			
			
		
		
		
		
		
		
		
		============	附: Spring execution 表达式 ===========================
   例   @Pointcut("execution(* cat.dao.impl.UserDaoImpl.add*(..))||"   过滤所有add打头的方法
							+ "execution(* cat.dao.impl.UserDaoImpl.upd*(..))||"  地滤所有的upd打头的方法
							+ "execution(* cat.dao.impl.UserDaoImpl.del*(..))" 
										
		execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
		除了返回类型模式（上面代码片断中的ret-type-pattern），名字模式和参数模式以外，所有的部分都是可选的。 返回类型模式决定了方法的返回类型必须依次匹配一个连接点。 你会使用的最频繁的返回类型模式是 *，它代表了匹配任意的返回类型。 一个全称限定的类型名将只会匹配返回给定类型的方法。名字模式匹配的是方法名。 你可以使用 * 通配符作为所有或者部分命名模式。 参数模式稍微有点复杂：() 匹配了一个不接受任何参数的方法， 而 (..) 匹配了一个接受任意数量参数的方法（零或者更多）。 模式 (*) 匹配了一个接受一个任何类型的参数的方法。 模式 (*,String) 匹配了一个接受两个参数的方法，第一个可以是任意类型，第二个则必须是String类型。
		
		下面给出一些常见切入点表达式的例子。
		
		任意公共方法的执行：
		execution(public * *(..)) 
		任何一个以“set”开始的方法的执行：
		execution(* set*(..)) 
		AccountService 接口的任意方法的执行：
		execution(* com.xyz.service.AccountService.*(..)) 
		定义在service包里的任意方法的执行：
		execution(* com.xyz.service.*.*(..)) 
		定义在service包或者子包里的任意方法的执行：
		execution(* com.xyz.service..*.*(..)) 
		在service包里的任意连接点（在Spring AOP中只是方法执行） ：
		within(com.xyz.service.*) 
		在service包或者子包里的任意连接点（在Spring AOP中只是方法执行） ：
		within(com.xyz.service..*) 
		实现了 AccountService 接口的代理对象的任意连接点（在Spring AOP中只是方法执行） ：
		this(com.xyz.service.AccountService)
		'this'在binding form中用的更多：- 请常见以下讨论通知的章节中关于如何使得代理对象可以在通知体内访问到的部分。 
		实现了 AccountService 接口的目标对象的任意连接点（在Spring AOP中只是方法执行） ：
		target(com.xyz.service.AccountService)
		'target'在binding form中用的更多：- 请常见以下讨论通知的章节中关于如何使得目标对象可以在通知体内访问到的部分。 
		任何一个只接受一个参数，且在运行时传入的参数实现了 Serializable 接口的连接点 （在Spring AOP中只是方法执行） 
		args(java.io.Serializable)
		'args'在binding form中用的更多：- 请常见以下讨论通知的章节中关于如何使得方法参数可以在通知体内访问到的部分。 请注意在例子中给出的切入点不同于 execution(* *(java.io.Serializable))： args只有在动态运行时候传入参数是可序列化的（Serializable）才匹配，而execution 在传入参数的签名声明的类型实现了 Serializable 接口时候匹配。 
		有一个 @Transactional 注解的目标对象中的任意连接点（在Spring AOP中只是方法执行） 
		@target(org.springframework.transaction.annotation.Transactional)
		'@target' 也可以在binding form中使用：请常见以下讨论通知的章节中关于如何使得annotation对象可以在通知体内访问到的部分。 
		任何一个目标对象声明的类型有一个 @Transactional 注解的连接点（在Spring AOP中只是方法执行）
		@within(org.springframework.transaction.annotation.Transactional)
		'@within'也可以在binding form中使用：- 请常见以下讨论通知的章节中关于如何使得annotation对象可以在通知体内访问到的部分。 
		任何一个执行的方法有一个 @Transactional annotation的连接点（在Spring AOP中只是方法执行） 
		@annotation(org.springframework.transaction.annotation.Transactional)
		'@annotation' 也可以在binding form中使用：- 请常见以下讨论通知的章节中关于如何使得annotation对象可以在通知体内访问到的部分。 
		任何一个接受一个参数，并且传入的参数在运行时的类型实现了 @Classified annotation的连接点（在Spring AOP中只是方法执行）
				 				 
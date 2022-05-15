======================================Lesson8 ============================================
1 maven 介绍
2 maven 项目的结构
3 坐标
4 在eclipse中如何配置maven
5 在eclipse中创建 mavenweb 项目
==========================================================================================
==== 1 maven 介绍
		
		maven是一个项目生命周期管理工具, 是 apache的一个开源的项目 
		
		--构建项目骨架
		--清理
		--编译
		--测试
		--生成测试报告
		--打包
		--发布
		--生成文档
		--生成帮助站点
		...
		
		对我们来说,我们更关心的是用它解决项目中的依赖问题
		
		1)  下载 https://maven.apache.org/
		     maven-3.8.5-bin.zip   8.35M大小
		     
		
		2) 安装
		     直接解压到想安装的位置就可以
		     我的是解压到   D:\Program Files\apache-maven-3.8.5
		     
		     要注意, 要使用maven,要先安装好JDK,并配置好环境变量 JAVA_HOME
		
		3) 把MAVEN 配置到环境变量中
		     在环境变量中,添加 D:\Program Files\apache-maven-3.8.5\bin; 这个路径就可以
		     
		     当然,也可以用 MAVEN_HOME 的方式配置
		      MAVEN_HOME=D:\Program Files\apache-maven-3.8.5;
		      Path=%MAVEN_HOME%\bin;
		      
		4) 在命令行中,执行 mvn -version 
					Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
					Maven home: D:\Program Files\apache-maven-3.8.5
					Java version: 1.8.0_271, vendor: Oracle Corporation, runtime: D:\Program Files\J
					ava\jdk1.8.0_271\jre
					Default locale: zh_CN, platform encoding: GBK
					OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"
					
					
					再执行命令 > mvn help:system
	  
	  5) 重要的配置,配置本地仓库的位置
	       默认情况下,它下载的内容,放在当前登录的用户的用户目录下的 .m2 中的 repository 中
	       比如我的就是: 
	       C:\Users\Administrator\.m2\repository
	       
	       可以进行修改它的配置文件 
	         D:\Program Files\apache-maven-3.8.5\conf\settings.xml 
	         中有如下配置:
	         
				  <!-- localRepository
				   | The path to the local repository maven will use to store artifacts.
				   |
				   | Default: ${user.home}/.m2/repository
				  <localRepository>/path/to/local/repo</localRepository>
				  -->
				  
				  可以这样进行配置
				  <localRepository>D:/maven-repository</localRepository>
		
		6) 配置镜象仓库的位置
				  D:\Program Files\apache-maven-3.8.5\conf\settings.xml 
	        
	        在 <mirrors> 节点下 添加如下配置
	        
			     <mirror>
				      <id>nexus-aliyun</id>
				      <mirrorOf>central</mirrorOf>
				      <name>Nexus aliyun</name>
				      <url>http://maven.aliyun.com/nexus/content/groups/public</url>
				   </mirror>
				   
==== 2 maven 项目的结构
		 maven项目的固定结构
		    /src/main/java   //放java代码
		    /src/main/resources  //放资源文件,比如各种配置文件
		    
		    /src/test/java  //放测试用的代码
		    /src/test/resources  //放测试用的资源文件
		    
		    /target //放产出来的产品, 比如编译出来的.class文件, 或 .jar 或 site 等
		    
		    /pom.xml //maven 的主配置文件
		    
		    
		   用命令直接生成项目骨架
		    >mvn archetype:generate 
		    
		    == 选择一个骨架类型 
		     默认是7 (quick start)   直接回车就可以
		    == 输入 groupId //组织名称 com.highcom
		    == 输入 artifactId //产品的名称  这里输入 oa 
		    == 输入 version //输入版本号,默认就可以 
		    == 输入 包名  //可以随意输入, 我输入的是cat
		    == 输入 Y 
		       GAV  坐标,用于在仓库中唯一定位一个组件
		       
==== 3 坐标
		GAV, 用来唯一标识一个组件在仓库中的位置
		
		它的组成
		  <groupId></groupId>   //组织名称
		  <artifactId></artifactId>  //组件的名称
		  <version></version> //版本
		
		 坐标的检索  https://mvnrepository.com 
		 
		 小常识,如何把项目中用到的依赖jar包复制出来 
		 
		 在项目上,右键 选 Run As ,选第二个Maven Build ,然后在上面的 Goals 对应的框中输入:	 
		  dependency:copy-dependencies -DoutputDirectory=lib
		 
		 执行完以后,可以发现,它把所有依赖的jar包都复制到了lib目录下
		 
		 关于项目生命周期管理的几个常用 Maven 命令
		 
		   >mvn clean   //清理 ,清理上次生成的内容
		   >mvn compile //编译
		   >mvn test  //运行测试
		   >mvn package  //打包
		   >mvn install  //把项目布署到本地仓库中 
		   >mvn site //生成相关的帮助网站
		   
		   注意这里，下面的命令都是依赖于上面的命令执行，比如test前必须先编译，但是clean不会自动执行
		   我们可以在ecplise中执行maven命令
		   在项目上,右键 选 Run As ,选第二个Maven Build ,然后在上面的 Goals 对应的框中输入相关的命令即可
		    比如输入
		    clean
		    claen package
		    clean install  
		    ...
		    但要注意,不要写成 mvn clean 
		      
==== 4 在eclipse中如何配置maven
    1) 在eclipse中配置maven路径 
       window-> preference --Maven --> installation
       点右边的Add, 找到maven的安装位置,添加maven, 添完以后,一定不要忘了把它前面的复选框勾选上
       
    2) window-> preference --Maven --> User Settings 
        把上面的Global Settings 和 下面的 User Settings 都指向maven的配置文件就可以
             
	    在建maven项目的时候,经常会出现由于组件没有下载下来而出错的情况
	    出现这种情况,我们可以重新更新几次项目
	     方式是在工程上,右键 Maven  - Update Project 
	     
	     有时候,多次更新也无效  会出现  ...  was cached in local Repository ....
	     可以去本地仓库中,把这些 缓存的相关信息清理掉
	      到本地仓库中执行     
	      D:\maven-repository>for /r %i in (*.lastUpdated) do del %i   它的作用是清理所有以 *.lastUpdated 结尾的文件
	 	    
	     如果以上方法还解决不了,可把把本地仓库删除掉,换别人的,好用的上来
	
	
==== 5 在eclipse中创建 mavenweb 项目
    1) 创建一个maven工程,类型选择 mave-webapp 
    2) 我们发现它默认的JDK是 1.5 ,可以配置插件进行管理
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
					    
					  配置以后,要进行一下更新 
			
		 3) 我们发现,它生成的工程中,看不到 src/main/java 和 src/test/java 目录
		    最简单的处理方式是把jdk移除重新导入一次
		    
		 
		 4) 我们发生,它生成的 index.jsp 中有错误
		     因为没有引入javaee相关的依赖,我们可以手动把相关的依赖引入
		     
		     //关于servlet的支持
		        <dependency>
		            <groupId>javax.servlet</groupId>
		            <artifactId>javax.servlet-api</artifactId>
		            <version>3.1.0</version>
		            <scope>provided</scope>
		        </dependency>
		     

		     //关于JSP的支持
		        <dependency>
		            <groupId>javax.servlet.jsp</groupId>
		            <artifactId>javax.servlet.jsp-api</artifactId>
		            <version>2.3.1</version>
		        </dependency>
		     
		     
		     //关于JSTL 的支持
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
								 
		5) 我们在index.jsp中写入el表达式,发现它不能解析
				 <%
				 	request.setAttribute("msg", "System is ok") ;
				 %>
				
				${msg }
				
				发布项目到tomcat上,访问  http://localhost:8080/web-system/index.jsp
				发现没有解析 ${msg},而是按原样输出了
				
				我们可以把web.xml中的内容进行一下修改,把它换成下面的样子
				<?xml version="1.0" encoding="UTF-8"?>
				<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
					xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
					xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
				
				</web-app>
				
		6) 如果有必要 
		    修改工程目录中 的 .settings 目录下的 org.eclipse.wst.common.project.facet.core.xml
		
				<?xml version="1.0" encoding="UTF-8"?>
				<faceted-project>
				  <fixed facet="wst.jsdt.web"/>
				  <installed facet="jst.web" version="3.1"/>  //原来是2.3 要换成3.1
				  <installed facet="wst.jsdt.web" version="1.0"/>
				  <installed facet="java" version="1.8"/>
				</faceted-project>
				
								
										     
										   
		    
															   
												
												

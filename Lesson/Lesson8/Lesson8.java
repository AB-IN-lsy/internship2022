======================================Lesson8 ============================================
1 maven ����
2 maven ��Ŀ�Ľṹ
3 ����
4 ��eclipse���������maven
5 ��eclipse�д��� mavenweb ��Ŀ
==========================================================================================
==== 1 maven ����
		
		maven��һ����Ŀ�������ڹ�����, �� apache��һ����Դ����Ŀ 
		
		--������Ŀ�Ǽ�
		--����
		--����
		--����
		--���ɲ��Ա���
		--���
		--����
		--�����ĵ�
		--���ɰ���վ��
		...
		
		��������˵,���Ǹ����ĵ������������Ŀ�е���������
		
		1)  ���� https://maven.apache.org/
		     maven-3.8.5-bin.zip   8.35M��С
		     
		
		2) ��װ
		     ֱ�ӽ�ѹ���밲װ��λ�þͿ���
		     �ҵ��ǽ�ѹ��   D:\Program Files\apache-maven-3.8.5
		     
		     Ҫע��, Ҫʹ��maven,Ҫ�Ȱ�װ��JDK,�����úû������� JAVA_HOME
		
		3) ��MAVEN ���õ�����������
		     �ڻ���������,��� D:\Program Files\apache-maven-3.8.5\bin; ���·���Ϳ���
		     
		     ��Ȼ,Ҳ������ MAVEN_HOME �ķ�ʽ����
		      MAVEN_HOME=D:\Program Files\apache-maven-3.8.5;
		      Path=%MAVEN_HOME%\bin;
		      
		4) ����������,ִ�� mvn -version 
					Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
					Maven home: D:\Program Files\apache-maven-3.8.5
					Java version: 1.8.0_271, vendor: Oracle Corporation, runtime: D:\Program Files\J
					ava\jdk1.8.0_271\jre
					Default locale: zh_CN, platform encoding: GBK
					OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"
					
					
					��ִ������ > mvn help:system
	  
	  5) ��Ҫ������,���ñ��زֿ��λ��
	       Ĭ�������,�����ص�����,���ڵ�ǰ��¼���û����û�Ŀ¼�µ� .m2 �е� repository ��
	       �����ҵľ���: 
	       C:\Users\Administrator\.m2\repository
	       
	       ���Խ����޸����������ļ� 
	         D:\Program Files\apache-maven-3.8.5\conf\settings.xml 
	         ������������:
	         
				  <!-- localRepository
				   | The path to the local repository maven will use to store artifacts.
				   |
				   | Default: ${user.home}/.m2/repository
				  <localRepository>/path/to/local/repo</localRepository>
				  -->
				  
				  ����������������
				  <localRepository>D:/maven-repository</localRepository>
		
		6) ���þ���ֿ��λ��
				  D:\Program Files\apache-maven-3.8.5\conf\settings.xml 
	        
	        �� <mirrors> �ڵ��� �����������
	        
			     <mirror>
				      <id>nexus-aliyun</id>
				      <mirrorOf>central</mirrorOf>
				      <name>Nexus aliyun</name>
				      <url>http://maven.aliyun.com/nexus/content/groups/public</url>
				   </mirror>
				   
==== 2 maven ��Ŀ�Ľṹ
		 maven��Ŀ�Ĺ̶��ṹ
		    /src/main/java   //��java����
		    /src/main/resources  //����Դ�ļ�,������������ļ�
		    
		    /src/test/java  //�Ų����õĴ���
		    /src/test/resources  //�Ų����õ���Դ�ļ�
		    
		    /target //�Ų������Ĳ�Ʒ, ������������.class�ļ�, �� .jar �� site ��
		    
		    /pom.xml //maven ���������ļ�
		    
		    
		   ������ֱ��������Ŀ�Ǽ�
		    >mvn archetype:generate 
		    
		    == ѡ��һ���Ǽ����� 
		     Ĭ����7 (quick start)   ֱ�ӻس��Ϳ���
		    == ���� groupId //��֯���� com.highcom
		    == ���� artifactId //��Ʒ������  �������� oa 
		    == ���� version //����汾��,Ĭ�ϾͿ��� 
		    == ���� ����  //������������, ���������cat
		    == ���� Y 
		       GAV  ����,�����ڲֿ���Ψһ��λһ�����
		       
==== 3 ����
		GAV, ����Ψһ��ʶһ������ڲֿ��е�λ��
		
		�������
		  <groupId></groupId>   //��֯����
		  <artifactId></artifactId>  //���������
		  <version></version> //�汾
		
		 ����ļ���  https://mvnrepository.com 
		 
		 С��ʶ,��ΰ���Ŀ���õ�������jar�����Ƴ��� 
		 
		 ����Ŀ��,�Ҽ� ѡ Run As ,ѡ�ڶ���Maven Build ,Ȼ��������� Goals ��Ӧ�Ŀ�������:	 
		  dependency:copy-dependencies -DoutputDirectory=lib
		 
		 ִ�����Ժ�,���Է���,��������������jar�������Ƶ���libĿ¼��
		 
		 ������Ŀ�������ڹ���ļ������� Maven ����
		 
		   >mvn clean   //���� ,�����ϴ����ɵ�����
		   >mvn compile //����
		   >mvn test  //���в���
		   >mvn package  //���
		   >mvn install  //����Ŀ���𵽱��زֿ��� 
		   >mvn site //������صİ�����վ
		   
		   ע������������������������������ִ�У�����testǰ�����ȱ��룬����clean�����Զ�ִ��
		   ���ǿ�����ecplise��ִ��maven����
		   ����Ŀ��,�Ҽ� ѡ Run As ,ѡ�ڶ���Maven Build ,Ȼ��������� Goals ��Ӧ�Ŀ���������ص������
		    ��������
		    clean
		    claen package
		    clean install  
		    ...
		    ��Ҫע��,��Ҫд�� mvn clean 
		      
==== 4 ��eclipse���������maven
    1) ��eclipse������maven·�� 
       window-> preference --Maven --> installation
       ���ұߵ�Add, �ҵ�maven�İ�װλ��,���maven, �����Ժ�,һ����Ҫ���˰���ǰ��ĸ�ѡ��ѡ��
       
    2) window-> preference --Maven --> User Settings 
        �������Global Settings �� ����� User Settings ��ָ��maven�������ļ��Ϳ���
             
	    �ڽ�maven��Ŀ��ʱ��,����������������û��������������������
	    �����������,���ǿ������¸��¼�����Ŀ
	     ��ʽ���ڹ�����,�Ҽ� Maven  - Update Project 
	     
	     ��ʱ��,��θ���Ҳ��Ч  �����  ...  was cached in local Repository ....
	     ����ȥ���زֿ���,����Щ ����������Ϣ�����
	      �����زֿ���ִ��     
	      D:\maven-repository>for /r %i in (*.lastUpdated) do del %i   �������������������� *.lastUpdated ��β���ļ�
	 	    
	     ������Ϸ������������,�ɰѰѱ��زֿ�ɾ����,�����˵�,���õ�����
	
	
==== 5 ��eclipse�д��� mavenweb ��Ŀ
    1) ����һ��maven����,����ѡ�� mave-webapp 
    2) ���Ƿ�����Ĭ�ϵ�JDK�� 1.5 ,�������ò�����й���
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
					    
					  �����Ժ�,Ҫ����һ�¸��� 
			
		 3) ���Ƿ���,�����ɵĹ�����,������ src/main/java �� src/test/java Ŀ¼
		    ��򵥵Ĵ���ʽ�ǰ�jdk�Ƴ����µ���һ��
		    
		 
		 4) ���Ƿ���,�����ɵ� index.jsp ���д���
		     ��Ϊû������javaee��ص�����,���ǿ����ֶ�����ص���������
		     
		     //����servlet��֧��
		        <dependency>
		            <groupId>javax.servlet</groupId>
		            <artifactId>javax.servlet-api</artifactId>
		            <version>3.1.0</version>
		            <scope>provided</scope>
		        </dependency>
		     

		     //����JSP��֧��
		        <dependency>
		            <groupId>javax.servlet.jsp</groupId>
		            <artifactId>javax.servlet.jsp-api</artifactId>
		            <version>2.3.1</version>
		        </dependency>
		     
		     
		     //����JSTL ��֧��
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
								 
		5) ������index.jsp��д��el���ʽ,���������ܽ���
				 <%
				 	request.setAttribute("msg", "System is ok") ;
				 %>
				
				${msg }
				
				������Ŀ��tomcat��,����  http://localhost:8080/web-system/index.jsp
				����û�н��� ${msg},���ǰ�ԭ�������
				
				���ǿ��԰�web.xml�е����ݽ���һ���޸�,�����������������
				<?xml version="1.0" encoding="UTF-8"?>
				<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
					xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
					xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
				
				</web-app>
				
		6) ����б�Ҫ 
		    �޸Ĺ���Ŀ¼�� �� .settings Ŀ¼�µ� org.eclipse.wst.common.project.facet.core.xml
		
				<?xml version="1.0" encoding="UTF-8"?>
				<faceted-project>
				  <fixed facet="wst.jsdt.web"/>
				  <installed facet="jst.web" version="3.1"/>  //ԭ����2.3 Ҫ����3.1
				  <installed facet="wst.jsdt.web" version="1.0"/>
				  <installed facet="java" version="1.8"/>
				</faceted-project>
				
								
										     
										   
		    
															   
												
												

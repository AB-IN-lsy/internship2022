========================================Lesson6 ===========================================
1 ��������ֵ�Ĵ���
2 ���ݻ���
3 �û�ɾ��
4 �˳���¼
5 ��̬��Դ�ļ�������
6 restfull���
7 �ļ��ϴ�
===========================================================================================
==== 1 ��������ֵ�Ĵ���
    ҳ���ڷ��������ʱ��,��ΰ����ݴ��������(���Ʋ�),����ڿ��Ʋ����ģ�����ݵĴ��� 
    
    �ڿ��Ʋ�ķ�����,���������¼������͵Ĳ���
    
    1) ��ͳ��Servlet�г��õļ�������
    	 HttpServletRequest
    	 HttpServletResponse
    	 HttpSession 
    
    2) ���ڴ���ģ������ 
    	 Model
    	 ModelMap
    	 Map
    	 
    	 ��: �ø��ַ�ʽ����ģ������(����������)
				@RequestMapping(value="/login")
				public String login(String userName,String password, ModelMap m, Model model,Map map, HttpSession session) {
					if("admin".equals(userName) && "123".equals(password)) {
						 session.setAttribute("session_userName", userName);
						 
						 return "/main";	   //������ͼ = ǰ׺+�߼���ͼ+��׺  /WEB-INF/view/main.jsp
					}
					else {
						//������ʾ�������ַ�ʽ����ģ������
						m.put("msg","�˺Ż��������,��¼ʧ��");
						model.addAttribute("info","���Ǹ��׳�,���û������ǲ�ס");
						map.put("str", "��Ҫ��,˭�������е����");
						
						return "forward:/login.jsp";   
					}		
				}	
				
				�� login.jsp�� 
						    	 
			  
			  <form action="${pageContext.request.contextPath}/user/login" method="post">
			  	�˺�:<input name="userName"  >
			  	����:<input name="password"  >
			  	<input type="submit" value="��¼">
			  </form>
			  
			  ${msg}
			
				${info }
				
				${str }
				
				${session_userName }  //ע��,�����ȡsession�е�����
		
		3) pojo���� 
		   //�� �û����			
		   (1) main.jsp�еĵ����Ϸ�������
						
					<ul>
						<li> <a href="${pageContext.request.contextPath}/user/add" >�û����</a>  </li>
						<li> <a>�û��б�</a>  </li>
						<li> <a>�˳���¼</a>  </li>
					</ul>
					
					�� ${pageContext.request.contextPath} ���д��,�Ƕ�̬�Ļ�ȡϵͳ�� contextPath, ��ֹ����
																
																
			(2) ���Ʋ�
				//ת���û����ҳ��,ֻ����GET����
					@RequestMapping(value="/add", method=RequestMethod.GET)  //ע��,��ֻ����GET����
					public String gotoAdd() {
						return "user-add";
					}
					
					
			(3) ҳ��  WEB-INF/view/user-add.jsp
					<form action="${pageContext.request.contextPath}/user/add" method="post">
								�˺�:	<input name="userName" >  <br />
								����:	<input name="password" > <br />
								��ע:	<textarea  name="note"></textarea> <br />
									<input type="submit" value="�ύ"  onclick="return confirm('ȷ��Ҫ�ύ��')">
					</form>
					${msg} 
					
			(4) ���Ʋ��е���ӷ���
					@RequestMapping(value="/add", method=RequestMethod.POST)  //ע��,��ֻ����POST����
						public String add(UserInfo user, ModelMap m) {  //ע��,����ֱ���� pojo���͵Ĳ���
							dao.addUser(user);
							m.put("msg","�û���ӳɹ�");
							return "user-add";
						}
						
					���Կ���,�����������,����ֱ���� UserInfo user ����������,�Ϳ��Խ��յ������ύ����������
					ǰ���Ǳ��е��ֶ���Ҫ�� �Ͳ���������ֶ�����ͬ
					
				  �� �����������͵Ĵ���
				     == �� UserInfo ʵ�����ϼ�һ�����������ֶ� 
				       	private Date birthday;
				     == �����ݿ���,Ҫ��Datetime���Ͷ�Ӧ
				     
				     == �û����ҳ�����һ��Ԫ��
				        ����:  <input name="birthday"  >  <br />
				     
				     == ���Ʋ�:
								@RequestMapping(value="/add", method=RequestMethod.POST)
								public String add(UserInfo user, ModelMap m) {
									System.out.println(user.getBirthday());
									dao.addUser(user);
									m.put("msg","�û���ӳɹ�");
									return "user-add";
								}
						 ==����,����,��ӵ�ʱ��,�������� 2000-12-20 �ύ,���ֳ�����400 ���� 
						   ��ʵ,400������springmvc���ʱ�����Ĵ���,��Ҫ���ύ��������������
						   �ͷ������Ҫ���������Ͳ�ƥ����ɵ�
						    ����������Ҫ��ֵ����,���ͻ����ύ���ǷǷ��ַ���
						    
						   ����ʽ: ��ʵ�����е��ֶ�����ע��
								@DateTimeFormat(pattern = "yyyy-MM-dd")
								private Date birthday;
													 
				   ��:������������
				     ���������ύ��������Ժ���ֵ���������,ֻҪ��web.xml������һ���������Ϳ���
				     
					  <!-- ����һ����������Ĺ����� -->
					  <filter>
					  	<filter-name>codeFilter</filter-name>
					  	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
					  	<init-param>
					  		<param-name>encoding</param-name>
					  		<param-value>UTF-8</param-value>
					  	</init-param>
					  </filter>
					  <filter-mapping>
						  	<filter-name>codeFilter</filter-name> 
						  	<url-pattern>/*</url-pattern>                                   
					  </filter-mapping> 
																	  
					
	 4) ������
	      ����
				@RequestMapping(value="/login")
					public String login(String userName,String password, ModelMap m) {
						if("admin".equals(userName) && "123".equals(password)) {	 
							....
						}
						else {
					    ....
						}		
					}	
			 
			   �����õľ��Ǽ����� ,ǰ�����ύ�ֶα���Ϳ��Ʋ㷽�в�����������ͬ
			   
	5) ��������
		  //��  ɾ�����ѡ���û�
		  == user-manager.jsp
			    <form action="${pageContext.request.contextPath }/user/deleteUsers" method="post">
					<table>
						<tr>
							<td></td>
							<td>id</td>
							<td>�˺�</td>
							<td>����</td>
							<td>��ע</td>
						</tr>
						<c:forEach var="u" items="${userList }">
						<tr>
							<td><input type="checkbox" ></td>
							<td>${u.id}</td>
							<td>${u.userName}</td>
							<td>${u.password}</td>
							<td>${u.note}</td>
						</tr>
						</c:forEach>		
					</table>
					<input type="submit" value="ɾ����ѡ" onclick="return confirm('ȷ��Ҫɾ����ѡ�û���')">
				</form>
			
			== ���Ʋ�
				@RequestMapping("/deleteUsers")
				public String deleteUsers( Integer [] ids, ModelMap m ) {
					dao.deleteUsers(ids);
					
					List<UserInfo> userList= dao.getAll();		
					m.put("userList",userList);
					return "user-manager";		
				}
				
			== dao�� 
				//����id�б�ɾ������û�
				public void deleteUsers(Integer[] ids) {
					List<UserInfo> tmpList=new ArrayList<>();
					for(int id:ids) {
						for(UserInfo u: userList) {
							if(u.getId()==id) {
								tmpList.add(u);
							}
						}	
					}
					
					for(UserInfo u:tmpList) {
						userList.remove(u);
					}
				}	
						
										  
	6) List���� (��Ҫ���������޸�,��)
	    
	    
==== 2 ���ݻ���
			
		//�� �û��޸Ĺ��� 
		 1) ��user-manger.jsp�Ϸ�������
        <table>
					<tr>
						<td></td>
						<td>id</td>
						<td>�˺�</td>
						<td>����</td>
						<td>��ע</td>
						<td>����</td>
					</tr>
					<c:forEach var="u" items="${userList }">
					<tr>
						<td><input type="checkbox" name="ids" value="${u.id}"></td>
						<td>${u.id}</td>
						<td>${u.userName}</td>
						<td>${u.password}</td>
						<td>${u.note}</td>
						<td> <a href="${pageContext.request.contextPath }/user/update?id=${u.id}">�޸�</a>  | <a  href="">ɾ��</a></td>
					</tr>
					</c:forEach>		
				</table>
					 
		2) ���Ʋ�
				@RequestMapping(value="/update", method=RequestMethod.GET)
				public String goToUpdate(int id,ModelMap m) {
					UserInfo user= dao.getUserById(id);
					m.put("user",  user);
					return "user-update";		
				}
				
	  3) dao�� 
				public UserInfo getUserById(int id) {
						for(UserInfo u:userList) {
							if(u.getId()==id) {
								return u;
							}
						}
						
						return null;
					}
		
		4)  WEB-INF/view/user-update.jsp 
				<form action="���������" method="post">
						<input type="hidden" name="id" value="${user.id }" >
			 	�˺�:	<input name="userName" value="${user.userName }" >  <br />
				����:	<input name="password" value="${user.password }"> <br />
				��ע:	<textarea  name="note"> ${user.note }</textarea> <br />
				
			    <input type="submit" value="�ύ"  onclick="return confirm('ȷ��Ҫ�ύ��')">
				
				</form>
				
		5) �ύ��,�����������޸Ķ�
		    �����ύ��ַ:
		    		<form action="${pageContext.request.contextPath }/user/update" method="post">
		    		  ...
		    		</form>
		    		
		    		${msg}
		    		
	  6) ���Ʋ�
			
				//�������ύ��,�޸��û���Ϣ
				@RequestMapping(value="/update", method=RequestMethod.POST)
				public String update(UserInfo user,ModelMap m) {
					dao.updateUser(user);
					
					//�ش�����,Ŀ����Ϊ�˻���
					m.put("user", user);
					m.put("msg","�û��޸ĳɹ�");
					return "user-update";
				} 
				
				/*Ҳ������  @ModelAttribute ���ע��,�������ݻش�
				@RequestMapping(value="/update", method=RequestMethod.POST)
				public String update(@ModelAttribute("user") UserInfo user,ModelMap m) {
					dao.updateUser(user);
					m.put("msg","�û��޸ĳɹ���,����");
					return "user-update";
				} 
				*/
										
		 7) dao�� 		
				private static List<UserInfo> userList;
					
					static {
						userList=new ArrayList<UserInfo>();
						userList.add(new UserInfo(1,"admin","123","һ���û�"));
						userList.add(new UserInfo(2,"root","root","�����û�"));
						userList.add(new UserInfo(3,"sa","sa","�����û�"));
						userList.add(new UserInfo(4,"scott","scott","�ĺ��û�"));
					}
					
					//��ԭ���Ļ����µ�
					public void updateUser(UserInfo user) {
						for(int i=0;i<userList.size();i++) {
							if(userList.get(i).getId()==user.getId())
							{
								userList.set(i, user);
							}
						}
					}

==== 3 �û�ɾ��
   1) ������
			<tr>
				<td><input type="checkbox" name="ids" value="${u.id}"></td>
				<td>${u.id}</td>
				<td>${u.userName}</td>
				<td>${u.password}</td>
				<td>${u.note}</td>
				<td> 
					<a href="${pageContext.request.contextPath }/user/update?id=${u.id}">�޸�</a>  |
          <a href="${pageContext.request.contextPath }/user/delete?id=${u.id}" onclick="return confirm('ȷ��Ҫɾ���û���')">ɾ��</a>
        </td>
		 	</tr>
		 	
		
	2) ���Ʋ� 
				@RequestMapping("/delete")
					public String  delete(Integer id,ModelMap m) {
						dao.deleteUser(id);
						
						/* ����Ҳ���԰��û��б����²�ѯһ�·ŵ��������д���ȥ
						List<UserInfo> userList=dao.getAll();
						m.put("userList", userList);
						return "user-manager"; 
						*/
						
						return "forward:/user/getAll";
					}
					
					
==== 4 �˳���¼
    �˳���¼,��Ҫ���Ķ�������������ڷ���˵� session ��Ϣ
    
     1) ��������
		  <ul>
				<li> <a href="${pageContext.request.contextPath}/user/add" >�û����</a>  </li>
				<li> <a href="${pageContext.request.contextPath}/user/getAll" >�û��б�</a>  </li>
				<li> <a href="${pageContext.request.contextPath}/user/logout" onclick="return confirm('ȷ��Ҫ�˳���')">�˳���¼</a>  </li>
			</ul>
			
		 2) ���Ʋ�
			@RequestMapping("/logout")
			public String logout(HttpSession session) {
				//��session�е���������ȫ���
				session.invalidate();
				return "forward:/login.jsp";	
			}

==== 5 ��̬��Դ�ļ�������

	    ��: �� WebContent �½�һ��static Ŀ¼,��������еľ�̬��Դ
	       WebContent 
	           html
	           		 -welcome.html
	           css 
	               -style.clss
	           images
	               -cat.jpg
	               
	        welcome.html ������:
							<head>
								<link rel="stylesheet" type="text/css" href="../css/style.css" ></link>
							</head>
							<body>
								<h1>��Ϣ����ϵͳ</h1>
								<hr />
								
								<img src="../images/bird.jpg" >
								<img src="../images/cat.jpg" >
							
							</body>
							        
				  login.jsp ��Ҳ����һЩ��̬����:
						<html>
						<head>
						<meta charset="UTF-8">
						<title></title>
						 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css"></link>
						</head>
						<body>
						  <h1>��¼</h1>
						  <img src="${pageContext.request.contextPath}/static/images/cat.jpg"  >
						  
						  <form action="${pageContext.request.contextPath}/user/login" method="post">
						  	�˺�:<input name="userName"  >
						  	����:<input name="password"  >
						  	<input type="submit" value="��¼">
						  </form>
						  	
						</body>
						</html>
						
						
				����ֱ��������Ŀ,����login.jsp  ���Ƿ���ҳ���ܿ���,����������ͼƬ��css��ʽ��Ч
				���� static/html/welcome.html ,ֱ��404,�Ҳ���
				
				
				ԭ��:
				   springmvc���������е�����,���ھ�̬��Դ( html,js, css, ͼƬ) ,�����Ժ�,��û�ٴ���,������Ҫ����һ��ӳ��
			 
			     ����Ҫ����Ŀ��,���о�̬��Դ��ӳ�� 
			     
			     	<!-- ��̬��Դӳ�� -->
				    <mvc:resources location="/static/" mapping="/static/**"  />
				  
				  �ٷ���, �Ϳ��Կ�����ȷ��ҳ����
				  
				  
==== 6 restfull���
	     REST����ʣ���Roy Thomas Fielding����2000��Ĳ�ʿ�����������
			 HTTPЭ�飨1.0���1.1�棩����Ҫ����ߡ�Apache���������������֮һ��Apache�����ĵ�һ����ϯ
			 
			 ��������������о���Ҫ��ע�����Ƶķ��ࡢ��Ʒ������ݻ������ٿ͹۵�������ͬ�����ѡ���ϵͳ��Ϊ��Ӱ�졣
			 ���෴�أ������о���Ҫ��עϵͳ֮��ͨ����Ϊ��ϸ�ڡ���θĽ��ض�ͨ�Ż��Ƶı��֣�����������һ����ʵ��
			 �Ǿ��Ǹı�Ӧ�ó���Ļ������ȸı以��Э�飬����������и����Ӱ�졣����ƪ���µ�д��Ŀ�ģ��������ڷ��ϼܹ�ԭ���ǰ���£�
			 ��������������Ϊ������Ӧ������ļܹ���ƣ��õ�һ������ǿ�����ܺá�����ͨ�ŵļܹ�
			 
			 REST���� Representational State Transfer����д���Ҷ��������ķ����� "���ֲ�״̬ת��"��  
			 ���һ���ܹ�����RESTԭ�򣬾ͳ���Ϊ RESTful �ܹ�
			 
			 ���� Representational State Transfer (���ֲ�״̬ת��) �����
			 1)REST������"���ֲ�״̬ת��"�У�ʡ�������"���ֲ�"��ʵָ����"��Դ"��Resources����"���ֲ�"��
			 		�����ϵ�һ��������Ϣ,������һ��URI��ͳһ��Դ��λ����ָ����
       2) Representation ���ֲ�
         "��Դ"��һ����Ϣʵ�壬�������ж������ڱ�����ʽ�����ǰ�"��Դ"������ֳ�������ʽ����������"���ֲ�"��Representation����
         ���磬�ı�������txt��ʽ���֣�Ҳ������HTML��ʽ��XML��ʽ
         URIֻ������Դ��ʵ�壬������������ʽ���ϸ��˵����Щ��ַ����".html" ��׺���ǲ���Ҫ�� ��Ϊ�����׺����ʾ��ʽ������ "���ֲ�" 
         ���룬��URIӦ��ֻ����"��Դ"��λ�á����ľ��������ʽ��Ӧ����HTTP�����ͷ��Ϣ����Accept��Content-Type�ֶ�ָ����
         �������ֶβ��Ƕ�"���ֲ�"��������
         
       3) State Transfer ״̬ת��
					HTTPЭ�飬��һ����״̬Э�顣����ζ�ţ����е�״̬�������ڷ������ˡ���ˣ�����ͻ�����Ҫ������������
					����ͨ��ĳ���ֶΣ��÷������˷���"״̬ת��"��State Transfer����������ת���ǽ����ڱ��ֲ�֮�ϵģ����Ծ���"���ֲ�״̬ת��"��
					�ͻ����õ����ֶΣ�ֻ����HTTPЭ�顣������˵������HTTPЭ�����棬�ĸ���ʾ������ʽ�Ķ��ʣ�
					GET��POST��PUT��DELETE�����Ƿֱ��Ӧ���ֻ���������
				  GET������ȡ��Դ��POST�����½���Դ��Ҳ�������ڸ�����Դ����PUT����������Դ��DELETE����ɾ����Դ
				  
				  
				  URL:			   
				   http://localhost:8080/UserServlet?flag=delete&id=10  
				   Rest=>http://localhost:8080/UserServlet/10
				     
				   http://localhost:8080/UserServlet?flag=add&name=admin&password=123&note=foolish
				   Rest=>http://localhost:8080/UserServlet/admin/123/foolish
				   
				   
				��  Rest����ɾ��
				<a href="${pageContext.request.contextPath }/user/del/${u.id}" >Rest���ɾ��</a>
        
				@RequestMapping("/del/{xxx}")
				public String del(@PathVariable("xxx") Integer id ) {
					dao.deleteUser(id);
					return "forward:/user/getAll";
				}
	
				�� Rest�����������
				@RequestMapping("test/{a}/{b}/{c}")
				public String test(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") int c) {
					System.out.println(a);
					System.out.println(b);
					System.out.println(c);
					return "xxxxxx";
				}
				
				����  http://localhost:8080/springmvc-02/user/test/firstcat/seconddog/99
				
==== 7 �ļ��ϴ�
		  1) �����ļ� 
					<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
						<property name="defaultEncoding" value="UTF-8" />
						<property name="maxUploadSize" value="6000000" />
						<property name="uploadTempDir" value="uploadTempDir"  />
					</bean>
					
		  2) �ϴ�ҳ�� 
						<form action="${pageContext.request.contextPath}/user/add"  method="post" enctype="multipart/form-data">
						�˺�:	<input name="userName" >  <br />
						����:	<input name="password" > <br />
						��ע:	<textarea  name="note"></textarea> <br />
					  ��Ƭ: <input type=file name=""uploadphoto"" >
							<input type="submit" value="�ύ"  onclick="return confirm('ȷ��Ҫ�ύ��')">
						
						</form>
						${msg} 
									
					  Ҫע������
					  (1) ���ύ��ʽ������post
					  (2) enctype="multipart/form-data"
					  (3) ע���ļ��ϴ���� "uploadphoto" �������,�ڿ��Ʋ���Ҫ�õ��������
			
			3) ���Ʋ�				 
					@RequestMapping(value="/add", method=RequestMethod.POST)
					public String add(UserInfo user, MultipartFile uploadphoto, HttpServletRequest request,  ModelMap m) throws IllegalStateException, IOException {
						user.setPhoto(uploadphoto.getOriginalFilename());
						dao.addUser(user);
						
						System.out.println("photo.getContentType(): " + uploadphoto.getContentType());
						System.out.println("photo.getName(): " + uploadphoto.getName());  //�õ��ϴ��ֶε�����
						System.out.println("photo.getOriginalFilename(): " + uploadphoto.getOriginalFilename());  //�õ��ļ�����
						System.out.println("photo.getSize(): " + uploadphoto.getSize());  //�ļ���С
						
						//�õ����Ŀ¼����Ӧ������·��(�����ַ����web��������)
						String realPath =request.getServletContext().getRealPath("/upload-files");
						File destFile=new File(realPath,uploadphoto.getOriginalFilename());
						
						//�ϴ�
						uploadphoto.transferTo(destFile);
				
						m.put("msg","�û���ӳɹ�");	
						return "user-add";
					}
					
			4) ʵ���������һ���ֶ�,�����û�ͷ���ַ
			   	private String photo;
			   	
			   	
			   	
			5) �����ʾ��ͼƬ:
			    ���û��б�ҳ������ʾ:   
			  	<td><img src="${pageContext.request.contextPath }/upload-files/${u.photo }"  > </td>
			  	
			6) ��Ҫ�������������ļ���,�� upload-files ���Ŀ¼��һ��ӳ��,Ҫ��Ȼ������ͼƬ
			
			   <mvc:resources location="/upload-files/" mapping="/upload-files/**"  />
	
										
											
				   
	
   
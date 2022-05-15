========================================Lesson6 ===========================================
1 参数绑定与值的传递
2 数据回显
3 用户删除
4 退出登录
5 静态资源文件的引入
6 restfull风格
7 文件上传
===========================================================================================
==== 1 参数绑定与值的传递
    页面在发起请求的时候,如何把数据传给服务端(控制层),如何在控制层进行模型数据的传递 
    
    在控制层的方法上,可以用以下几种类型的参数
    
    1) 传统的Servlet中常用的几个类型
    	 HttpServletRequest
    	 HttpServletResponse
    	 HttpSession 
    
    2) 用于传递模型数据 
    	 Model
    	 ModelMap
    	 Map
    	 
    	 例: 用各种方式传递模型数据(作用域数据)
				@RequestMapping(value="/login")
				public String login(String userName,String password, ModelMap m, Model model,Map map, HttpSession session) {
					if("admin".equals(userName) && "123".equals(password)) {
						 session.setAttribute("session_userName", userName);
						 
						 return "/main";	   //物理视图 = 前缀+逻辑视图+后缀  /WEB-INF/view/main.jsp
					}
					else {
						//下面演示了用三种方式传递模型数据
						m.put("msg","账号或密码错误,登录失败");
						model.addAttribute("info","真是个白痴,连用户名都记不住");
						map.put("str", "不要紧,谁还不能有点错呢");
						
						return "forward:/login.jsp";   
					}		
				}	
				
				在 login.jsp上 
						    	 
			  
			  <form action="${pageContext.request.contextPath}/user/login" method="post">
			  	账号:<input name="userName"  >
			  	密码:<input name="password"  >
			  	<input type="submit" value="登录">
			  </form>
			  
			  ${msg}
			
				${info }
				
				${str }
				
				${session_userName }  //注意,这个是取session中的数据
		
		3) pojo类型 
		   //例 用户添加			
		   (1) main.jsp中的导航上发起请求
						
					<ul>
						<li> <a href="${pageContext.request.contextPath}/user/add" >用户添加</a>  </li>
						<li> <a>用户列表</a>  </li>
						<li> <a>退出登录</a>  </li>
					</ul>
					
					附 ${pageContext.request.contextPath} 这个写法,是动态的获取系统的 contextPath, 防止出错
																
																
			(2) 控制层
				//转向到用户添加页面,只处理GET请求
					@RequestMapping(value="/add", method=RequestMethod.GET)  //注意,它只处理GET请求
					public String gotoAdd() {
						return "user-add";
					}
					
					
			(3) 页面  WEB-INF/view/user-add.jsp
					<form action="${pageContext.request.contextPath}/user/add" method="post">
								账号:	<input name="userName" >  <br />
								密码:	<input name="password" > <br />
								备注:	<textarea  name="note"></textarea> <br />
									<input type="submit" value="提交"  onclick="return confirm('确定要提交吗')">
					</form>
					${msg} 
					
			(4) 控制层中的添加方法
					@RequestMapping(value="/add", method=RequestMethod.POST)  //注意,它只处理POST请求
						public String add(UserInfo user, ModelMap m) {  //注意,这里直接用 pojo类型的参数
							dao.addUser(user);
							m.put("msg","用户添加成功");
							return "user-add";
						}
						
					可以看到,上面的例子中,参数直接用 UserInfo user 这样的类型,就可以接收到表单中提交上来的数据
					前题是表单中的字段名要和 和参数对象的字段名相同
					
				  附 关于类期类型的处理
				     == 在 UserInfo 实体类上加一个日期类型字段 
				       	private Date birthday;
				     == 在数据库中,要用Datetime类型对应
				     
				     == 用户添加页面添加一个元素
				        生日:  <input name="birthday"  >  <br />
				     
				     == 控制层:
								@RequestMapping(value="/add", method=RequestMethod.POST)
								public String add(UserInfo user, ModelMap m) {
									System.out.println(user.getBirthday());
									dao.addUser(user);
									m.put("msg","用户添加成功");
									return "user-add";
								}
						 ==启动,运行,添加的时候,生日填入 2000-12-20 提交,发现出现了400 问题 
						   其实,400是在用springmvc框架时常见的错误,主要是提交的请求数据类型
						   和服务端想要的数据类型不匹配造成的
						    比如服务端想要数值类型,但客户端提交的是非法字符串
						    
						   处理方式: 在实体类中的字段上用注解
								@DateTimeFormat(pattern = "yyyy-MM-dd")
								private Date birthday;
													 
				   附:关于乱码问题
				     对于请求提交到服务端以后出现的中文乱码,只要在web.xml中配置一个过滤器就可以
				     
					  <!-- 配置一个处理乱码的过滤器 -->
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
																	  
					
	 4) 简单类型
	      例如
				@RequestMapping(value="/login")
					public String login(String userName,String password, ModelMap m) {
						if("admin".equals(userName) && "123".equals(password)) {	 
							....
						}
						else {
					    ....
						}		
					}	
			 
			   上例用的就是简单类型 ,前提是提交字段必须和控制层方中参数的名称相同
			   
	5) 数组类型
		  //例  删除多个选中用户
		  == user-manager.jsp
			    <form action="${pageContext.request.contextPath }/user/deleteUsers" method="post">
					<table>
						<tr>
							<td></td>
							<td>id</td>
							<td>账号</td>
							<td>密码</td>
							<td>备注</td>
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
					<input type="submit" value="删除所选" onclick="return confirm('确定要删除所选用户吗')">
				</form>
			
			== 控制层
				@RequestMapping("/deleteUsers")
				public String deleteUsers( Integer [] ids, ModelMap m ) {
					dao.deleteUsers(ids);
					
					List<UserInfo> userList= dao.getAll();		
					m.put("userList",userList);
					return "user-manager";		
				}
				
			== dao层 
				//根据id列表删除多个用户
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
						
										  
	6) List类型 (主要用于批量修改,略)
	    
	    
==== 2 数据回显
			
		//例 用户修改功能 
		 1) 从user-manger.jsp上发起请求
        <table>
					<tr>
						<td></td>
						<td>id</td>
						<td>账号</td>
						<td>密码</td>
						<td>备注</td>
						<td>操作</td>
					</tr>
					<c:forEach var="u" items="${userList }">
					<tr>
						<td><input type="checkbox" name="ids" value="${u.id}"></td>
						<td>${u.id}</td>
						<td>${u.userName}</td>
						<td>${u.password}</td>
						<td>${u.note}</td>
						<td> <a href="${pageContext.request.contextPath }/user/update?id=${u.id}">修改</a>  | <a  href="">删除</a></td>
					</tr>
					</c:forEach>		
				</table>
					 
		2) 控制层
				@RequestMapping(value="/update", method=RequestMethod.GET)
				public String goToUpdate(int id,ModelMap m) {
					UserInfo user= dao.getUserById(id);
					m.put("user",  user);
					return "user-update";		
				}
				
	  3) dao层 
				public UserInfo getUserById(int id) {
						for(UserInfo u:userList) {
							if(u.getId()==id) {
								return u;
							}
						}
						
						return null;
					}
		
		4)  WEB-INF/view/user-update.jsp 
				<form action="这里待处理" method="post">
						<input type="hidden" name="id" value="${user.id }" >
			 	账号:	<input name="userName" value="${user.userName }" >  <br />
				密码:	<input name="password" value="${user.password }"> <br />
				备注:	<textarea  name="note"> ${user.note }</textarea> <br />
				
			    <input type="submit" value="提交"  onclick="return confirm('确定要提交吗')">
				
				</form>
				
		5) 提交表单,进行真正的修改动
		    表单的提交地址:
		    		<form action="${pageContext.request.contextPath }/user/update" method="post">
		    		  ...
		    		</form>
		    		
		    		${msg}
		    		
	  6) 控制层
			
				//真正的提交表单,修改用户信息
				@RequestMapping(value="/update", method=RequestMethod.POST)
				public String update(UserInfo user,ModelMap m) {
					dao.updateUser(user);
					
					//回传数据,目的是为了回显
					m.put("user", user);
					m.put("msg","用户修改成功");
					return "user-update";
				} 
				
				/*也可以用  @ModelAttribute 这个注解,处理数据回传
				@RequestMapping(value="/update", method=RequestMethod.POST)
				public String update(@ModelAttribute("user") UserInfo user,ModelMap m) {
					dao.updateUser(user);
					m.put("msg","用户修改成功了,哈哈");
					return "user-update";
				} 
				*/
										
		 7) dao层 		
				private static List<UserInfo> userList;
					
					static {
						userList=new ArrayList<UserInfo>();
						userList.add(new UserInfo(1,"admin","123","一号用户"));
						userList.add(new UserInfo(2,"root","root","二号用户"));
						userList.add(new UserInfo(3,"sa","sa","三号用户"));
						userList.add(new UserInfo(4,"scott","scott","四号用户"));
					}
					
					//把原来的换成新的
					public void updateUser(UserInfo user) {
						for(int i=0;i<userList.size();i++) {
							if(userList.get(i).getId()==user.getId())
							{
								userList.set(i, user);
							}
						}
					}

==== 3 用户删除
   1) 请求发起
			<tr>
				<td><input type="checkbox" name="ids" value="${u.id}"></td>
				<td>${u.id}</td>
				<td>${u.userName}</td>
				<td>${u.password}</td>
				<td>${u.note}</td>
				<td> 
					<a href="${pageContext.request.contextPath }/user/update?id=${u.id}">修改</a>  |
          <a href="${pageContext.request.contextPath }/user/delete?id=${u.id}" onclick="return confirm('确定要删除用户吗')">删除</a>
        </td>
		 	</tr>
		 	
		
	2) 控制层 
				@RequestMapping("/delete")
					public String  delete(Integer id,ModelMap m) {
						dao.deleteUser(id);
						
						/* 我们也可以把用户列表重新查询一下放到作用域中传过去
						List<UserInfo> userList=dao.getAll();
						m.put("userList", userList);
						return "user-manager"; 
						*/
						
						return "forward:/user/getAll";
					}
					
					
==== 4 退出登录
    退出登录,主要作的动作就是清除留在服务端的 session 信息
    
     1) 发起请求
		  <ul>
				<li> <a href="${pageContext.request.contextPath}/user/add" >用户添加</a>  </li>
				<li> <a href="${pageContext.request.contextPath}/user/getAll" >用户列表</a>  </li>
				<li> <a href="${pageContext.request.contextPath}/user/logout" onclick="return confirm('确定要退出吗')">退出登录</a>  </li>
			</ul>
			
		 2) 控制层
			@RequestMapping("/logout")
			public String logout(HttpSession session) {
				//将session中的所有数据全清除
				session.invalidate();
				return "forward:/login.jsp";	
			}

==== 5 静态资源文件的引入

	    例: 在 WebContent 下建一个static 目录,里面放所有的静态资源
	       WebContent 
	           html
	           		 -welcome.html
	           css 
	               -style.clss
	           images
	               -cat.jpg
	               
	        welcome.html 的内容:
							<head>
								<link rel="stylesheet" type="text/css" href="../css/style.css" ></link>
							</head>
							<body>
								<h1>信息管理系统</h1>
								<hr />
								
								<img src="../images/bird.jpg" >
								<img src="../images/cat.jpg" >
							
							</body>
							        
				  login.jsp 中也引入一些静态内容:
						<html>
						<head>
						<meta charset="UTF-8">
						<title></title>
						 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css"></link>
						</head>
						<body>
						  <h1>登录</h1>
						  <img src="${pageContext.request.contextPath}/static/images/cat.jpg"  >
						  
						  <form action="${pageContext.request.contextPath}/user/login" method="post">
						  	账号:<input name="userName"  >
						  	密码:<input name="password"  >
						  	<input type="submit" value="登录">
						  </form>
						  	
						</body>
						</html>
						
						
				现在直接启动项目,访问login.jsp  我们发现页面能看到,但里面引的图片和css样式无效
				访问 static/html/welcome.html ,直接404,找不到
				
				
				原因:
				   springmvc拦截了所有的请求,对于静态资源( html,js, css, 图片) ,拦下以后,就没再处理,我们需要进行一下映射
			 
			     我们要在项目中,进行静态资源的映射 
			     
			     	<!-- 静态资源映射 -->
				    <mvc:resources location="/static/" mapping="/static/**"  />
				  
				  再访问, 就可以看到正确的页面了
				  
				  
==== 6 restfull风格
	     REST这个词，是Roy Thomas Fielding在他2000年的博士论文中提出的
			 HTTP协议（1.0版和1.1版）的主要设计者、Apache服务器软件的作者之一、Apache基金会的第一任主席
			 
			 长期以来，软件研究主要关注软件设计的分类、设计方法的演化，很少客观地评估不同的设计选择对系统行为的影响。
			 而相反地，网络研究主要关注系统之间通信行为的细节、如何改进特定通信机制的表现，常常忽视了一个事实，
			 那就是改变应用程序的互动风格比改变互动协议，对整体表现有更大的影响。我这篇文章的写作目的，就是想在符合架构原理的前提下，
			 理解和评估以网络为基础的应用软件的架构设计，得到一个功能强、性能好、适宜通信的架构
			 
			 REST，即 Representational State Transfer的缩写。我对这个词组的翻译是 "表现层状态转化"。  
			 如果一个架构符合REST原则，就称它为 RESTful 架构
			 
			 关于 Representational State Transfer (表现层状态转化) 的理解
			 1)REST的名称"表现层状态转化"中，省略了主语。"表现层"其实指的是"资源"（Resources）的"表现层"。
			 		网络上的一个具体信息,可以用一个URI（统一资源定位符）指向它
       2) Representation 表现层
         "资源"是一种信息实体，它可以有多种外在表现形式。我们把"资源"具体呈现出来的形式，叫做它的"表现层"（Representation）。
         比如，文本可以用txt格式表现，也可以用HTML格式、XML格式
         URI只代表资源的实体，不代表它的形式。严格地说，有些网址最后的".html" 后缀名是不必要的 因为这个后缀名表示格式，属于 "表现层" 
         范畴，而URI应该只代表"资源"的位置。它的具体表现形式，应该在HTTP请求的头信息中用Accept和Content-Type字段指定，
         这两个字段才是对"表现层"的描述。
         
       3) State Transfer 状态转化
					HTTP协议，是一个无状态协议。这意味着，所有的状态都保存在服务器端。因此，如果客户端想要操作服务器，
					必须通过某种手段，让服务器端发生"状态转化"（State Transfer）。而这种转化是建立在表现层之上的，所以就是"表现层状态转化"。
					客户端用到的手段，只能是HTTP协议。具体来说，就是HTTP协议里面，四个表示操作方式的动词：
					GET、POST、PUT、DELETE。它们分别对应四种基本操作：
				  GET用来获取资源，POST用来新建资源（也可以用于更新资源），PUT用来更新资源，DELETE用来删除资源
				  
				  
				  URL:			   
				   http://localhost:8080/UserServlet?flag=delete&id=10  
				   Rest=>http://localhost:8080/UserServlet/10
				     
				   http://localhost:8080/UserServlet?flag=add&name=admin&password=123&note=foolish
				   Rest=>http://localhost:8080/UserServlet/admin/123/foolish
				   
				   
				例  Rest风格的删除
				<a href="${pageContext.request.contextPath }/user/del/${u.id}" >Rest风格删除</a>
        
				@RequestMapping("/del/{xxx}")
				public String del(@PathVariable("xxx") Integer id ) {
					dao.deleteUser(id);
					return "forward:/user/getAll";
				}
	
				例 Rest风格传入多个参数
				@RequestMapping("test/{a}/{b}/{c}")
				public String test(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") int c) {
					System.out.println(a);
					System.out.println(b);
					System.out.println(c);
					return "xxxxxx";
				}
				
				访问  http://localhost:8080/springmvc-02/user/test/firstcat/seconddog/99
				
==== 7 文件上传
		  1) 配置文件 
					<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
						<property name="defaultEncoding" value="UTF-8" />
						<property name="maxUploadSize" value="6000000" />
						<property name="uploadTempDir" value="uploadTempDir"  />
					</bean>
					
		  2) 上传页面 
						<form action="${pageContext.request.contextPath}/user/add"  method="post" enctype="multipart/form-data">
						账号:	<input name="userName" >  <br />
						密码:	<input name="password" > <br />
						备注:	<textarea  name="note"></textarea> <br />
					  照片: <input type=file name=""uploadphoto"" >
							<input type="submit" value="提交"  onclick="return confirm('确定要提交吗')">
						
						</form>
						${msg} 
									
					  要注意两点
					  (1) 表单提交方式必须是post
					  (2) enctype="multipart/form-data"
					  (3) 注意文件上传组件 "uploadphoto" 这个名字,在控制层中要用到这个名称
			
			3) 控制层				 
					@RequestMapping(value="/add", method=RequestMethod.POST)
					public String add(UserInfo user, MultipartFile uploadphoto, HttpServletRequest request,  ModelMap m) throws IllegalStateException, IOException {
						user.setPhoto(uploadphoto.getOriginalFilename());
						dao.addUser(user);
						
						System.out.println("photo.getContentType(): " + uploadphoto.getContentType());
						System.out.println("photo.getName(): " + uploadphoto.getName());  //得到上传字段的名称
						System.out.println("photo.getOriginalFilename(): " + uploadphoto.getOriginalFilename());  //得到文件名称
						System.out.println("photo.getSize(): " + uploadphoto.getSize());  //文件大小
						
						//得到这个目录所对应的物理路径(这个地址是在web服务器上)
						String realPath =request.getServletContext().getRealPath("/upload-files");
						File destFile=new File(realPath,uploadphoto.getOriginalFilename());
						
						//上传
						uploadphoto.transferTo(destFile);
				
						m.put("msg","用户添加成功");	
						return "user-add";
					}
					
			4) 实体类中添加一个字段,代表用户头象地址
			   	private String photo;
			   	
			   	
			   	
			5) 如果显示出图片:
			    在用户列表页面中显示:   
			  	<td><img src="${pageContext.request.contextPath }/upload-files/${u.photo }"  > </td>
			  	
			6) 不要忘了在主配置文件中,把 upload-files 这个目录做一下映射,要不然看不到图片
			
			   <mvc:resources location="/upload-files/" mapping="/upload-files/**"  />
	
										
											
				   
	
   
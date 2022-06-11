=======================================Lesson17 =========================================
1 关于联动下拉框
2 关于项目功能 //见视频
3 关于图片处理
4 用户添加功能
5 用户列表查询
=========================================================================================
==== 1 关于联动下拉框
    1) 引入json相关的依赖
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-databind</artifactId>
	            <version>2.5.0</version>
	        </dependency>
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-core</artifactId>
	            <version>2.5.0</version>
	        </dependency>
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-annotations</artifactId>
	            <version>2.5.0</version>
	        </dependency>
	
	        <dependency>
	            <groupId>org.json</groupId>
	            <artifactId>json</artifactId>
	            <version>20170516</version>
	        </dependency>
	        
	  2) 控制层
			@Controller @RequestMapping("/category")
			public class CategoryController {
				@Resource
				private CategoryService categoryService;
				
				//查询所有的了分类,以 json格式返回
				@ResponseBody  @RequestMapping("/listSub")
				public List<Category> getSubCategoryList(Integer parentId){
					 List<Category>  categoryList=categoryService.listCategoryByParentId(parentId);
					 return categoryList;
				}	
			}

    3) CategoryService 略
    
       CategoryServiceImpl  如下
       
				@Service
				public class CategoryServiceImpl implements CategoryService {
				
					@Resource
					private CategoryMapper categoryMapper;
					
					//查询所有的分类信息
					public List<Category> listCategory() {
						return categoryMapper.listCategory();
					}
					
					//根据父级id,查询子分类信息
					public List<Category> listCategoryByParentId(Integer parentId) {
						return categoryMapper.listCategoryByParentId(parentId);
					}
				}

   4)  CategoryMapper.xml 中
				<!-- 查询某个分类的所有子分类 -->
				<select id="listCategoryByParentId"   resultType="Category">
				    select  <include refid="cols" /> from category where category_pid=#{parentId}
				</select>
				
	
	 5) 页面

			//二级联动
			form.on(
					"select(articleParentCategoryId)",
					function() {
						var pid= $("#articleParentCategoryId").val();
							$.ajax({
							url:"category/listSub",
							type:"post",
							data:{parentId:pid},
							dataType:"json",
							
							success:function(cateList){
								$("#articleChildCategoryId").empty();
								$("#articleChildCategoryId").append("<option value=''>二级分类</option>");
								for(var i=0;i<cateList.length;i++){
									var cate=cateList[i];
									
									var str="<option value='"+cate.categoryId+"'> "+cate.categoryName+"</option>";
								
									$("#articleChildCategoryId").append(str);
								}	
								
								form.render('select'); //这个很重要
							}
						});
					})
				});
	 
	 
==== 3 关于图片处理
    系统中用到的图片,要上传到服务端 ,图在服务器上怎么存放呢? 
    
       1) 图片直接上传到服务端的文件系统中
          
          一般来说,是在服务端创建一个虚拟目录,然后把图片放在服务器和这个虚拟目录相关的路径下
          引用的时候
          <img src="服务器端的图片地址" > 比如 <img src="uploade/a.jpg" >
          
          优点: 处理简单,图片放在服务器的某个目录中,管理方便一些,图片体积大小问题不大,可以放高清图片
          
          
          缺点: 图片和数据的关联性小,比如删除数据的时候,很容易忘记删除数据对应的图片
                系统迁移的时候,容易丢失图片
       
       2) 图片数据直接存到数据库中
          优点: 图片和普通数据一样管理,一致性特别好
               系统迁移的时候也不会丢失图片
               公安局用的人员档案信息,图片就非常适合放到数据库
               
          缺点: 如果图片大,数据库压力增大,数据库中存的图片,不容易查看
          
          
==== 4 用户添加功能
    1)  数据库字段和实体类字段的准备
         public class User {
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
				    
				    //主要是下面这个字段
				    private byte [] userPhoto;  //用户照片,存的是具体的照片数据   
				    ...
				    
				    //以前漏了这个字段,要添上
				    private Integer articleCount;  //用户发布的文章数量
				 }
				 
				 数据库中添加字段,可以用 longblob 类型
				 
				    user_photo 类型为  longblob
				    
		2) 用户添加 
		   
		     (1) 在 framework.jsp上发起请求
		                <dd><a href="user/add">添加用户</a></dd>
		                
		     (2) 控制层 
						
						/**
						 * 从导航上转到用户添加页面
						 */
						@RequestMapping(value="/add", method=RequestMethod.GET)
						public String goToAdd() {
							return "User/user-add";
						}
						
				 (3) User/user-add.jsp
							<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
							<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
							
							<rapid:override name="frame-content">
								<blockquote class="layui-elem-quote">
									<span class="layui-breadcrumb" lay-separator="/"> 
										<a href="/admin">首页</a> <a href="/admin/user">用户列表</a> <a><cite>编辑用户</cite></a>
									</span>
								</blockquote>
								<br>
								<br>
								<form class="layui-form" action="user/add" 	id="userForm" method="post" enctype="multipart/form-data">
									<div class="layui-form-item">
										<label 	class="layui-form-label">头像</label>
										<div class="layui-input-inline">
											<div class="layui-upload">
												<div class="layui-upload-list" id="localImag" >
													<img class="layui-upload-img"   id="demo1" width="100" 	height="100"  >
												</div>
										    	
												<input type="file" name="photo" id="photo" type="file" style="display:none" onchange="preview(this,localImag,demo1,'100px','100px')">
												<button type="button"  onclick="$('#photo').click()"  class="layui-btn" id="test1">上传图片</button>
											</div>
										</div>
										<input type="hidden" name="userAvatar" id="userAvatar"  value="">
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">用户名 <span
											style="color: #FF5722;">*</span></label>
										<div class="layui-input-inline">
											<input type="text" name="userName" id="userName" required 	lay-verify="userName" autocomplete="off" class="layui-input" onblur="checkUserName()">
										</div>
										<div class="layui-form-mid layui-word-aux" id="userNameTips"></div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">密码 <span style="color: #FF5722;">*</span></label>
										<div class="layui-input-inline">
											<input type="password" name="userPass" id="userPass" required 	lay-verify="userPass" autocomplete="off" class="layui-input" 	min="3" max="20">
										</div>
										<div class="layui-form-mid layui-word-aux"></div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">昵称 <span style="color: #FF5722;">*</span></label>
										<div class="layui-input-inline">
											<input type="text" name="userNickname" required placeholder="" 	autocomplete="off" min="2" max="10" class="layui-input">
										</div>
										<div class="layui-form-mid layui-word-aux"></div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">Email <span style="color: #FF5722;">*</span></label>		
										<div class="layui-input-inline">
											<input type="email" name="userEmail" id="userEmail" required 	lay-verify="userEmail" class="layui-input" 	onblur="checkUserEmail()">
										</div>
										<div class="layui-form-mid layui-word-aux" id="userEmailTips"></div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">URL </label>
										<div class="layui-input-inline">
											<input type="url" name="userUrl" placeholder="" class="layui-input">
										</div>
									</div>
							
									<div class="layui-form-item">
										<div class="layui-input-block">
											<button class="layui-btn" lay-submit lay-filter="demo1" id="submit-btn">保存</button>
											<button type="reset" class="layui-btn layui-btn-primary">重置</button>
										</div>
									</div>
								</form>
							</rapid:override>
							
							<rapid:override name="frame-footer-script">
								<script>
								
								  //预览图片  
								  function preview(docObj, localImagId, imgObjPreview, width, height) {
										if (docObj.files && docObj.files[0]) { //火狐下，直接设img属性        
											imgObjPreview.style.display = 'block';
											imgObjPreview.style.width = width;
											imgObjPreview.style.height = height;
											//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式        
											imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
										} else { //IE下，使用滤镜      
											docObj.select();
											var imgSrc = document.selection.createRange().text;
											//必须设置初始大小        
											localImagId.style.width = width;
											localImagId.style.height = height;
											//图片异常的捕捉，防止用户修改后缀来伪造图片        
											try {
												localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
												localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
											} catch (e) {
												alert("您上传的图片格式不正确，请重新选择!");
												return false;
											}
											imgObjPreview.style.display = 'none';
											document.selection.empty();
										}
									}
							 	
								</script>
							</rapid:override>
							
							<%@ include file="../framework.jsp" %>
							
					
					 要注意: 由于添加数据的时候,要上传图片,所以表单的method 和   enctype必须按下面的方式配置
					    method="post" enctype="multipart/form-data"
								
					下面这个图片上传选择框,要注意它的name属性在服务端是要用的			它的name属性,不要和实体类中的照片那个字段 userPhoto 相同 					    
	         <input type="file" name="photo" id="photo" type="file" style="display:none" onchange="preview(this,localImag,demo1,'100px','100px')">
	         
	         
	        (4) UserController
	        	@RequestMapping(value="/add", method=RequestMethod.POST)
						public String add(User user, MultipartFile photo) throws IOException {	
							user.setUserRegisterTime(new Date());
							user.setUserStatus(1);
							user.setUserPhoto( photo.getBytes() );
							
							userService.addUser(user);	
							return "forward:/user";
						}
						
				  (5) UseService 略
				  
				  (6) UserServiceImpl 
							@Service
							public class UserServiceImpl implements UserService {
							
								@Resource
								private UserMapper userMapper;
								
								public User loginByNameOrEmail(String s) {
									return userMapper.loginByNameOrEmail(s);
								}
							
								public void addUser(User user) {
									 userMapper.addUser(user);
								}
							}
							
					(7) UserMapper 略
					(8) UserMapper.xml 
					
					
					  要注意,添加上 user_photo这个列
						<sql id="cols">
					    user_id, user_name, user_pass, user_nickname, user_email, user_url, user_avatar, 
				  	  user_last_login_ip, user_register_time, user_last_login_time, user_status,user_photo
				  	</sql>

						  <!-- 添加用户,含有照片 -->
					    <insert id="addUser" parameterType="User" >
					        insert into   user
					        (user_id, user_name, user_pass,
					        user_nickname, user_email, user_url,
					        user_avatar, user_last_login_ip, user_register_time,
					        user_last_login_time, user_status,user_photo)
					        values (#{userId}, #{userName}, #{userPass},
					        #{userNickname}, #{userEmail}, #{userUrl},
					        #{userAvatar}, #{userLastLoginIp}, #{userRegisterTime},
					        #{userLastLoginTime}, #{userStatus},#{userPhoto})
					    </insert>
					    
==== 5 用户列表查询
     1) framework.jsp上发起请求
           <dd><a href="user">全部用户</a></dd>
          
     2) 控制层 
					@RequestMapping("")
					public String userList(ModelMap map) {
						List<User> userList=userService.listUser();
						map.put("userList",userList);
						return "User/user-list";
					}
								  
		 3) UserService 略
		 
		 4) UserServiceImpl
				@Service
				public class UserServiceImpl implements UserService {
				
					@Resource
					private UserMapper userMapper;
					
					public User loginByNameOrEmail(String s) {
						return userMapper.loginByNameOrEmail(s);
					}
				
				
					public void addUser(User user) {
						 userMapper.addUser(user);
					}
	
					public List<User> listUser() {
						return userMapper.listUser();
					}
				
				}		
				
			5) UserMapper略	
			
			6) UserMapper.xml 
		    <select id="listUser">
		    	select  <include refid="cols" /> from user where     order by user_id desc 
		    </select>				
		    
		  7) User/user-list.jsp 中呈现数据
						<%@ page language="java" contentType="text/html; charset=UTF-8"
							pageEncoding="UTF-8"%>
						<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
						<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
						
						<rapid:override name="frame-content">
							<blockquote class="layui-elem-quote">
								<span class="layui-breadcrumb" lay-separator="/"> <a
									href="/admin">首页</a> <a><cite>用户列表</cite></a>
								</span>
							</blockquote>
						
							<table class="layui-table" lay-even lay-skin="nob">
								<colgroup>
									<col width="100">
									<col width="100">
									<col width="100">
									<col width="50">
									<col width="50">
									<col width="100">
									<col width="50">
								</colgroup>
								<thead>
									<tr>
										<th>用户名</th>
										<th>昵称</th>
										<th>电子邮件</th>
										<th>文章</th>
										<th>状态</th>
										<th>操作</th>
										<th>ID</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach  var="u" items="${userList}">
										<tr>
											<td>
												<img src="user/photo/${u.userId }" width="48" height="48"> 
												 <strong><a href="user/edit/${u.userId}">${u.userName}</a></strong>
											</td>
											<td>${u.userNickname}</td>
											<td>${u.userEmail}</td>
											<td>${u.articleCount}</td>
											<td>
												<c:choose>
													<c:when test="${u.userStatus==0}">
														<span style="color: #FF5722;">禁用</span>
													</c:when>
													<c:otherwise>
						                     			   正常
						                  		   </c:otherwise>
												</c:choose>
											</td>
											<td>
												<a href="user/edit/${u.userId}" class="layui-btn layui-btn-mini">编辑</a>
												<a  href="user/delete/${u.userId}" 	class="layui-btn layui-btn-danger layui-btn-mini" 	onclick="return confirmDelete()">删除</a>
											</td>
											<td>${u.userId}</td>
										</tr>
						
									</c:forEach>
								</tbody>
							</table>
						</rapid:override>
						<%@ include file="../framework.jsp"%>
						
						
						注意 		<img src="user/photo/${u.userId }" width="48" height="48">   这句
						         这个img就是用户图片,它指向的是一个服务端的地址
								  
		  8) 	控制层中输出图片数据的方式
		  					  					
					@RequestMapping("/photo/{userId}")
					public void showPhoto(@PathVariable("userId") Integer userId,HttpServletResponse response) throws IOException {
						User user=userService.getUserById(userId);
						
						response.setContentType("image/jpg");
						ServletOutputStream out =response.getOutputStream();
						out.write(user.getUserPhoto());
						out.flush();
					}
					
			9) UserServie,  UserServiceImpl, UserMapper, UserMapper.xml 中的 getUserById 方法略, 请自行实现
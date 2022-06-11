=======================================Lesson17 =========================================
1 ��������������
2 ������Ŀ���� //����Ƶ
3 ����ͼƬ����
4 �û���ӹ���
5 �û��б��ѯ
=========================================================================================
==== 1 ��������������
    1) ����json��ص�����
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
	        
	  2) ���Ʋ�
			@Controller @RequestMapping("/category")
			public class CategoryController {
				@Resource
				private CategoryService categoryService;
				
				//��ѯ���е��˷���,�� json��ʽ����
				@ResponseBody  @RequestMapping("/listSub")
				public List<Category> getSubCategoryList(Integer parentId){
					 List<Category>  categoryList=categoryService.listCategoryByParentId(parentId);
					 return categoryList;
				}	
			}

    3) CategoryService ��
    
       CategoryServiceImpl  ����
       
				@Service
				public class CategoryServiceImpl implements CategoryService {
				
					@Resource
					private CategoryMapper categoryMapper;
					
					//��ѯ���еķ�����Ϣ
					public List<Category> listCategory() {
						return categoryMapper.listCategory();
					}
					
					//���ݸ���id,��ѯ�ӷ�����Ϣ
					public List<Category> listCategoryByParentId(Integer parentId) {
						return categoryMapper.listCategoryByParentId(parentId);
					}
				}

   4)  CategoryMapper.xml ��
				<!-- ��ѯĳ������������ӷ��� -->
				<select id="listCategoryByParentId"   resultType="Category">
				    select  <include refid="cols" /> from category where category_pid=#{parentId}
				</select>
				
	
	 5) ҳ��

			//��������
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
								$("#articleChildCategoryId").append("<option value=''>��������</option>");
								for(var i=0;i<cateList.length;i++){
									var cate=cateList[i];
									
									var str="<option value='"+cate.categoryId+"'> "+cate.categoryName+"</option>";
								
									$("#articleChildCategoryId").append(str);
								}	
								
								form.render('select'); //�������Ҫ
							}
						});
					})
				});
	 
	 
==== 3 ����ͼƬ����
    ϵͳ���õ���ͼƬ,Ҫ�ϴ�������� ,ͼ�ڷ���������ô�����? 
    
       1) ͼƬֱ���ϴ�������˵��ļ�ϵͳ��
          
          һ����˵,���ڷ���˴���һ������Ŀ¼,Ȼ���ͼƬ���ڷ��������������Ŀ¼��ص�·����
          ���õ�ʱ��
          <img src="�������˵�ͼƬ��ַ" > ���� <img src="uploade/a.jpg" >
          
          �ŵ�: �����,ͼƬ���ڷ�������ĳ��Ŀ¼��,������һЩ,ͼƬ�����С���ⲻ��,���ԷŸ���ͼƬ
          
          
          ȱ��: ͼƬ�����ݵĹ�����С,����ɾ�����ݵ�ʱ��,����������ɾ�����ݶ�Ӧ��ͼƬ
                ϵͳǨ�Ƶ�ʱ��,���׶�ʧͼƬ
       
       2) ͼƬ����ֱ�Ӵ浽���ݿ���
          �ŵ�: ͼƬ����ͨ����һ������,һ�����ر��
               ϵͳǨ�Ƶ�ʱ��Ҳ���ᶪʧͼƬ
               �������õ���Ա������Ϣ,ͼƬ�ͷǳ��ʺϷŵ����ݿ�
               
          ȱ��: ���ͼƬ��,���ݿ�ѹ������,���ݿ��д��ͼƬ,�����ײ鿴
          
          
==== 4 �û���ӹ���
    1)  ���ݿ��ֶκ�ʵ�����ֶε�׼��
         public class User {
				  	private Integer userId;  //����id 
				    private String userName; //�˺�  
				    private String userPass;  //����
				    private String userNickname;  //�ǳ�
				    private String userEmail;  //����
				    private String userUrl;  //�û�����ַ
				    private String userAvatar;  //ָ��һ��ͼƬ��ַ(����ֶ�Ŀǰֻ�Ǳ����ֶ�,��ʱ����)
				    private String userLastLoginIp;   //����¼��IP
				    private Date userRegisterTime;   //�û�ע���ʱ��
				    private Date userLastLoginTime; //����¼��ʱ��
				    private Integer userStatus; //�û���״̬
				    
				    //��Ҫ����������ֶ�
				    private byte [] userPhoto;  //�û���Ƭ,����Ǿ������Ƭ����   
				    ...
				    
				    //��ǰ©������ֶ�,Ҫ����
				    private Integer articleCount;  //�û���������������
				 }
				 
				 ���ݿ�������ֶ�,������ longblob ����
				 
				    user_photo ����Ϊ  longblob
				    
		2) �û���� 
		   
		     (1) �� framework.jsp�Ϸ�������
		                <dd><a href="user/add">����û�</a></dd>
		                
		     (2) ���Ʋ� 
						
						/**
						 * �ӵ�����ת���û����ҳ��
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
										<a href="/admin">��ҳ</a> <a href="/admin/user">�û��б�</a> <a><cite>�༭�û�</cite></a>
									</span>
								</blockquote>
								<br>
								<br>
								<form class="layui-form" action="user/add" 	id="userForm" method="post" enctype="multipart/form-data">
									<div class="layui-form-item">
										<label 	class="layui-form-label">ͷ��</label>
										<div class="layui-input-inline">
											<div class="layui-upload">
												<div class="layui-upload-list" id="localImag" >
													<img class="layui-upload-img"   id="demo1" width="100" 	height="100"  >
												</div>
										    	
												<input type="file" name="photo" id="photo" type="file" style="display:none" onchange="preview(this,localImag,demo1,'100px','100px')">
												<button type="button"  onclick="$('#photo').click()"  class="layui-btn" id="test1">�ϴ�ͼƬ</button>
											</div>
										</div>
										<input type="hidden" name="userAvatar" id="userAvatar"  value="">
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">�û��� <span
											style="color: #FF5722;">*</span></label>
										<div class="layui-input-inline">
											<input type="text" name="userName" id="userName" required 	lay-verify="userName" autocomplete="off" class="layui-input" onblur="checkUserName()">
										</div>
										<div class="layui-form-mid layui-word-aux" id="userNameTips"></div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">���� <span style="color: #FF5722;">*</span></label>
										<div class="layui-input-inline">
											<input type="password" name="userPass" id="userPass" required 	lay-verify="userPass" autocomplete="off" class="layui-input" 	min="3" max="20">
										</div>
										<div class="layui-form-mid layui-word-aux"></div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">�ǳ� <span style="color: #FF5722;">*</span></label>
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
											<button class="layui-btn" lay-submit lay-filter="demo1" id="submit-btn">����</button>
											<button type="reset" class="layui-btn layui-btn-primary">����</button>
										</div>
									</div>
								</form>
							</rapid:override>
							
							<rapid:override name="frame-footer-script">
								<script>
								
								  //Ԥ��ͼƬ  
								  function preview(docObj, localImagId, imgObjPreview, width, height) {
										if (docObj.files && docObj.files[0]) { //����£�ֱ����img����        
											imgObjPreview.style.display = 'block';
											imgObjPreview.style.width = width;
											imgObjPreview.style.height = height;
											//���7���ϰ汾�����������getAsDataURL()��ʽ��ȡ����Ҫһ�·�ʽ        
											imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
										} else { //IE�£�ʹ���˾�      
											docObj.select();
											var imgSrc = document.selection.createRange().text;
											//�������ó�ʼ��С        
											localImagId.style.width = width;
											localImagId.style.height = height;
											//ͼƬ�쳣�Ĳ�׽����ֹ�û��޸ĺ�׺��α��ͼƬ        
											try {
												localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
												localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
											} catch (e) {
												alert("���ϴ���ͼƬ��ʽ����ȷ��������ѡ��!");
												return false;
											}
											imgObjPreview.style.display = 'none';
											document.selection.empty();
										}
									}
							 	
								</script>
							</rapid:override>
							
							<%@ include file="../framework.jsp" %>
							
					
					 Ҫע��: ����������ݵ�ʱ��,Ҫ�ϴ�ͼƬ,���Ա���method ��   enctype���밴����ķ�ʽ����
					    method="post" enctype="multipart/form-data"
								
					�������ͼƬ�ϴ�ѡ���,Ҫע������name�����ڷ������Ҫ�õ�			����name����,��Ҫ��ʵ�����е���Ƭ�Ǹ��ֶ� userPhoto ��ͬ 					    
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
						
				  (5) UseService ��
				  
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
							
					(7) UserMapper ��
					(8) UserMapper.xml 
					
					
					  Ҫע��,����� user_photo�����
						<sql id="cols">
					    user_id, user_name, user_pass, user_nickname, user_email, user_url, user_avatar, 
				  	  user_last_login_ip, user_register_time, user_last_login_time, user_status,user_photo
				  	</sql>

						  <!-- ����û�,������Ƭ -->
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
					    
==== 5 �û��б��ѯ
     1) framework.jsp�Ϸ�������
           <dd><a href="user">ȫ���û�</a></dd>
          
     2) ���Ʋ� 
					@RequestMapping("")
					public String userList(ModelMap map) {
						List<User> userList=userService.listUser();
						map.put("userList",userList);
						return "User/user-list";
					}
								  
		 3) UserService ��
		 
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
				
			5) UserMapper��	
			
			6) UserMapper.xml 
		    <select id="listUser">
		    	select  <include refid="cols" /> from user where     order by user_id desc 
		    </select>				
		    
		  7) User/user-list.jsp �г�������
						<%@ page language="java" contentType="text/html; charset=UTF-8"
							pageEncoding="UTF-8"%>
						<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
						<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
						
						<rapid:override name="frame-content">
							<blockquote class="layui-elem-quote">
								<span class="layui-breadcrumb" lay-separator="/"> <a
									href="/admin">��ҳ</a> <a><cite>�û��б�</cite></a>
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
										<th>�û���</th>
										<th>�ǳ�</th>
										<th>�����ʼ�</th>
										<th>����</th>
										<th>״̬</th>
										<th>����</th>
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
														<span style="color: #FF5722;">����</span>
													</c:when>
													<c:otherwise>
						                     			   ����
						                  		   </c:otherwise>
												</c:choose>
											</td>
											<td>
												<a href="user/edit/${u.userId}" class="layui-btn layui-btn-mini">�༭</a>
												<a  href="user/delete/${u.userId}" 	class="layui-btn layui-btn-danger layui-btn-mini" 	onclick="return confirmDelete()">ɾ��</a>
											</td>
											<td>${u.userId}</td>
										</tr>
						
									</c:forEach>
								</tbody>
							</table>
						</rapid:override>
						<%@ include file="../framework.jsp"%>
						
						
						ע�� 		<img src="user/photo/${u.userId }" width="48" height="48">   ���
						         ���img�����û�ͼƬ,��ָ�����һ������˵ĵ�ַ
								  
		  8) 	���Ʋ������ͼƬ���ݵķ�ʽ
		  					  					
					@RequestMapping("/photo/{userId}")
					public void showPhoto(@PathVariable("userId") Integer userId,HttpServletResponse response) throws IOException {
						User user=userService.getUserById(userId);
						
						response.setContentType("image/jpg");
						ServletOutputStream out =response.getOutputStream();
						out.write(user.getUserPhoto());
						out.flush();
					}
					
			9) UserServie,  UserServiceImpl, UserMapper, UserMapper.xml �е� getUserById ������, ������ʵ��
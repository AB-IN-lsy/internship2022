=======================================Lesson16 ===========================================
1 文章发布
2 乱码处理
3 富文本编辑器
4 图片上传的配置
===========================================================================================
==== 1 文章发布
   1) 引入 hutool 工具包  
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>4.1.13</version>
        </dependency>
            
   2) 文章实体类中,要包含有分类相关的信息和标签相关的信息 
    
			    //文章属于哪些分类(属于哪个大分类和哪个小分类)
			    private List<Category> categoryList;
			    
			    //文章属于哪些标签
			    private List<Tag> tagList;
			    
			    
			    同时,给 Category 类添加一个构造方法
					public class Category {
						public Category(){}
						public Category(Integer categoryId){
							this.categoryId=categoryId;
						}
					}
					
					同时,也可给 Tag 类添加一个构造方法
					public class Tag {
						public Tag(){}
						public Tag( Integer tagId) {
							this.tagId=tagId;
						}
					}
	
	 3) 请求发起
	      从 article-add.jsp 的表单上发起
	      <form class="layui-form" method="post" id="myForm" 	action="article/add">
	      
	      
	      附: 以前 页面中的标签列表展示的位置  value="${t.tagName }" 这个写法错误,要如下修正:
      	<c:forEach var="t" items="${tagList }">
					<input type="checkbox" name="articleTagIds" lay-skin="primary" title="${t.tagName }" value="${t.tagId }"> 
				</c:forEach>
				
	 
	 4) 在控制层 ArticleController 中 进行入理
			@RequestMapping(value="/add",method=RequestMethod.POST)
			public String add(HttpServletRequest request) {
				
				Article article  =new Article();
				
				//当前用户的id
				User user=(User)request.getSession().getAttribute("session_user");
				article.setArticleUserId(user.getUserId());
				
				//文章标题
				article.setArticleTitle(request.getParameter("articleTitle"));
				
				//文章内容
				article.setArticleContent(request.getParameter("articleContent"));
				
				//文章摘要
				String s=HtmlUtil.cleanHtmlTag(article.getArticleContent()) ;
				article.setArticleSummary(s.length()>150?s.substring(0,150):s);
			
				//文章的发布时间,修改时间
				article.setArticleCreateTime(new Date());
				article.setArticleUpdateTime(new Date());
				
				article.setArticleCommentCount(0);
				article.setArticleLikeCount(0);
				article.setArticleViewCount(0);
				
				//默认的排序
				article.setArticleOrder(1);
				
				//文章的状态
				article.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));
				
				//一级分类
				int articleParentCategoryId =Integer.parseInt(request.getParameter("articleParentCategoryId"));
				
				//二级分类
				int articleChildCategoryId =Integer.parseInt(request.getParameter("articleChildCategoryId"));
				
				List<Category>categoryList=new ArrayList<Category> (2);
				categoryList.add(new Category(articleParentCategoryId));
				categoryList.add(new Category(articleChildCategoryId));
				article.setCategoryList(categoryList);
				
				//标签
				String [] tagIds=request.getParameterValues("articleTagIds");
				List<Tag> tagList =new ArrayList<>();
				for(String tagId:tagIds) {
					tagList.add(new Tag(Integer.parseInt(tagId)));
				}
				article.setTagList(tagList);
				
				
				//数据准备好以后,调用业务层
				articleService.add(article);
				
				//转到文章列表页
				return "fowrard:/article" ;
				
			}
			
		5) 业务层接口和实现
		
		    接口略
		    
		    实现类 ArticleServiceImpl 中的方法:
		    
				public void add(Article article) {
					//往文章表中添数据
					articleMapper.addArticle(article);
					
					//往文章分类表中添数据
					List<Category>categorylist=article.getCategoryList();
					for(Category c:categorylist ) {
						articleMapper.addArticleCategory(article.getArticleId(), c.getCategoryId());
					}
					
					//往文章标签表中添数据
					List<Tag> tagList=article.getTagList();
					for(Tag t: tagList) {
						articleMapper.addArticleTag(article.getArticleId(),t.getTagId());
					}
				}
		
   6) ArticleMapper 中的方法:
				/**
					 * 往文章表中添加数据
					 * @param article ,可以用来接收返回的自增主键
					 */
					void addArticle(Article article);
				
					/**
					 * 往文章分类表中添加数据
					 * @param articleId 文章id
					 * @param categoryId 分类id
					 */
					void addArticleCategory(@Param("articleId") Integer articleId, @Param("categoryId")  Integer categoryId);
				
					/**
					 * 往文章标签表中添加数据
					 * @param articleId 文章id
					 * @param tagId 标签id
					 */
					void addArticleTag(@Param("articleId")  Integer articleId, @Param("tagId")  Integer tagId);
					
	 7) ArticleMapper.xml 中的实现
			  <!-- 发布文章,要记得需要返回生成的自主键 -->
				<insert id="addArticle" parameterType="Article" useGeneratedKeys="true" keyProperty="articleId">
				    insert into article
			        (article_user_id, article_title,
			        article_view_count, article_comment_count,
			        article_like_count, article_create_time, article_update_time,
			        article_is_comment, article_status, article_order,
			        article_content, article_summary)
			        values (#{articleUserId},
			         #{articleTitle},
			        #{articleViewCount},
			        #{articleCommentCount},
			        #{articleLikeCount}, 
			        #{articleCreateTime},
			        #{articleUpdateTime},
			        #{articleIsComment},
			        #{articleStatus}, 
			        #{articleOrder},
			        #{articleContent},
			        #{articleSummary})
				</insert>
				
				<!-- 往文章分类表中添加数据 -->
				<insert id="addArticleCategory">
					insert into article_category_ref (article_id,category_id)  values (#{articleId}, #{categoryId}) 
				</insert>
				
				<!-- 往文章标签表中添加数据 -->
				<insert id="addArticleTag">
					insert into article_tag_ref (article_id,tag_id)  values (#{articleId}, #{tagId}) 
				</insert>
						
					
==== 2 乱码处理
		在web.xml中配置一个过滤器处理一下请求乱码就可以
		
		  <filter>
			    <filter-name>CharacterEncodingFilter</filter-name>
			    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
			    <!-- 设置过滤器中的属性值 -->
			    <init-param>
			      <param-name>encoding</param-name>
			      <param-value>UTF-8</param-value>
			    </init-param>
			    <!-- 启动过滤器 -->
			    <init-param>
			      <param-name>forceEncoding</param-name>
			      <param-value>true</param-value>
			    </init-param>
		  </filter>
		  <filter-mapping>
			    <filter-name>CharacterEncodingFilter</filter-name>
			    <url-pattern>/*</url-pattern>                                                         
		  </filter-mapping>
		  
		  
		  */
		  
==== 3 富文本编辑器

		KindEditor是基于浏览器的所见即所得(WYSWYG)HTML编辑器，主要应用于CMS、论坛、博客等WEB程序里。 
		主要特点： 
		  1) 代码量少，功能比较多。 
		  2) 通过添加plugin的方法，可以自定义功能。 
		  3) 可以删除不需要的plugin，减少文件大小。 
		  4) 可以任意改变编辑器风格，和网站融为一体。 
		  5) 代码容易理解，是一个可维护、可控制的编辑器
		  
		  
		  1) 下载 ,解压 把  KindEditor 复制到工程中的 resources 目录下
				
		  2) 引入相关的 css,js文件
					<rapid:override name="frame-header-script">
						<link rel="stylesheet" href="resources/kindeditor/themes/default/default.css" />
						<link rel="stylesheet" href="resources/kindeditor/plugins/code/prettify.css" />
						<script charset="utf-8" src="resources/kindeditor/kindeditor-all-min.js"></script>
						<script charset="utf-8" src="resources/kindeditor/lang/zh-CN.js"></script>
						<script charset="utf-8" src="resources/kindeditor/plugins/code/prettify.js"></script>
					</rapid:override>
					 
		  3) 开启渲染
		      	<textarea  name="articleContent" lay-verify="content" id="content"></textarea>		
					<script>
						KindEditor.ready(function(K) {
							var editor1 = K.create('textarea[id="content"]', {
								allowFileManager : true,	
								
								width:"1100px",
								height:"420px"
							});
							prettyPrint();
						});
					</script>
				
     4) 把关于原来的 layui 中的editor的东西删除掉
     
     
====4 图片上传的配置
   1) KindEditor 中配置上传图片的路径
				
			<script>
				KindEditor.ready(function(K) {
					var editor1 = K.create('textarea[id="content"]', {
						allowFileManager : true,	
						width:"1100px",
						height:"420px",
						uploadJson : "article/uploadImg",   //指向的是一个服务端地址,用于图片上传
					});
					prettyPrint();
				});
			</script>
			
	2) 在springmvc.xml 中进行配置
	    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	       <!--最大尺寸为50MB-->
	    	<property name="maxUploadSize" value="52428800" />
	    	<property name="defaultEncoding" value="UTF-8" />
	    </bean>
	    
	3) 引入相关的依赖
      <!-- 文件上传 -->
      <dependency>
          <groupId>commons-fileupload</groupId>
          <artifactId>commons-fileupload</artifactId>
          <version>1.2.2</version>
      </dependency>
      <dependency>
          <groupId>commons-io</groupId>
          <artifactId>commons-io</artifactId>
          <version>2.4</version>
      </dependency>
      
  4) 在tomcat上配置虚拟路径
         打开tomcat的 server.xml 进行如下配置
	       <Host>
	         ....
	         <Context docBase="D:/imgupload/" path="/upload"  reloadable="true"  />
	       </Host>
	       
	      如果用的是sts ,则要在 Servers工程中进行配置
	      
	      配置完以后,可以在 D:/imgupload/中放几个图片,然后启动服务器,在浏览器地址栏上直接访问测试
	      
	      http://localhost:8080/upload/mice.jpg  能看到图片,证明配置成功了
	      
	 5) 图片上传控制层
				/**
				 * 富文本编辑器对应的图片上传
				 * @return 富文本编辑器要求的json类型的数据
				 * @throws IOException 
				 * @throws IllegalStateException 
				 */
				@ResponseBody @RequestMapping("/uploadImg")
				public String uploadArticleImg(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
					//得到客户端传过来的图片 , imgFile 是一个固定名称
					MultipartFile file= request.getFile("imgFile");
					
					//随机生成一个文件名
					String fileName=UUID.randomUUID().toString()+".jpg";
					
					//定义一个存放文件的目标
					File destFile=new File("d:/imgupload/"+fileName);
					
					//把文件存到某个目录下
					file.transferTo(destFile);
			
					String path="http://localhost:8080/upload/"+fileName;
					
					//注意,这个 JSONObject 是来源于 hutool 工具包
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", path);
			
					return  obj.toString();
				}
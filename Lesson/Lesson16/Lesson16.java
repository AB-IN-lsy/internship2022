=======================================Lesson16 ===========================================
1 ���·���
2 ���봦��
3 ���ı��༭��
4 ͼƬ�ϴ�������
===========================================================================================
==== 1 ���·���
   1) ���� hutool ���߰�  
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>4.1.13</version>
        </dependency>
            
   2) ����ʵ������,Ҫ�����з�����ص���Ϣ�ͱ�ǩ��ص���Ϣ 
    
			    //����������Щ����(�����ĸ��������ĸ�С����)
			    private List<Category> categoryList;
			    
			    //����������Щ��ǩ
			    private List<Tag> tagList;
			    
			    
			    ͬʱ,�� Category �����һ�����췽��
					public class Category {
						public Category(){}
						public Category(Integer categoryId){
							this.categoryId=categoryId;
						}
					}
					
					ͬʱ,Ҳ�ɸ� Tag �����һ�����췽��
					public class Tag {
						public Tag(){}
						public Tag( Integer tagId) {
							this.tagId=tagId;
						}
					}
	
	 3) ������
	      �� article-add.jsp �ı��Ϸ���
	      <form class="layui-form" method="post" id="myForm" 	action="article/add">
	      
	      
	      ��: ��ǰ ҳ���еı�ǩ�б�չʾ��λ��  value="${t.tagName }" ���д������,Ҫ��������:
      	<c:forEach var="t" items="${tagList }">
					<input type="checkbox" name="articleTagIds" lay-skin="primary" title="${t.tagName }" value="${t.tagId }"> 
				</c:forEach>
				
	 
	 4) �ڿ��Ʋ� ArticleController �� ��������
			@RequestMapping(value="/add",method=RequestMethod.POST)
			public String add(HttpServletRequest request) {
				
				Article article  =new Article();
				
				//��ǰ�û���id
				User user=(User)request.getSession().getAttribute("session_user");
				article.setArticleUserId(user.getUserId());
				
				//���±���
				article.setArticleTitle(request.getParameter("articleTitle"));
				
				//��������
				article.setArticleContent(request.getParameter("articleContent"));
				
				//����ժҪ
				String s=HtmlUtil.cleanHtmlTag(article.getArticleContent()) ;
				article.setArticleSummary(s.length()>150?s.substring(0,150):s);
			
				//���µķ���ʱ��,�޸�ʱ��
				article.setArticleCreateTime(new Date());
				article.setArticleUpdateTime(new Date());
				
				article.setArticleCommentCount(0);
				article.setArticleLikeCount(0);
				article.setArticleViewCount(0);
				
				//Ĭ�ϵ�����
				article.setArticleOrder(1);
				
				//���µ�״̬
				article.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));
				
				//һ������
				int articleParentCategoryId =Integer.parseInt(request.getParameter("articleParentCategoryId"));
				
				//��������
				int articleChildCategoryId =Integer.parseInt(request.getParameter("articleChildCategoryId"));
				
				List<Category>categoryList=new ArrayList<Category> (2);
				categoryList.add(new Category(articleParentCategoryId));
				categoryList.add(new Category(articleChildCategoryId));
				article.setCategoryList(categoryList);
				
				//��ǩ
				String [] tagIds=request.getParameterValues("articleTagIds");
				List<Tag> tagList =new ArrayList<>();
				for(String tagId:tagIds) {
					tagList.add(new Tag(Integer.parseInt(tagId)));
				}
				article.setTagList(tagList);
				
				
				//����׼�����Ժ�,����ҵ���
				articleService.add(article);
				
				//ת�������б�ҳ
				return "fowrard:/article" ;
				
			}
			
		5) ҵ���ӿں�ʵ��
		
		    �ӿ���
		    
		    ʵ���� ArticleServiceImpl �еķ���:
		    
				public void add(Article article) {
					//�����±���������
					articleMapper.addArticle(article);
					
					//�����·������������
					List<Category>categorylist=article.getCategoryList();
					for(Category c:categorylist ) {
						articleMapper.addArticleCategory(article.getArticleId(), c.getCategoryId());
					}
					
					//�����±�ǩ����������
					List<Tag> tagList=article.getTagList();
					for(Tag t: tagList) {
						articleMapper.addArticleTag(article.getArticleId(),t.getTagId());
					}
				}
		
   6) ArticleMapper �еķ���:
				/**
					 * �����±����������
					 * @param article ,�����������շ��ص���������
					 */
					void addArticle(Article article);
				
					/**
					 * �����·�������������
					 * @param articleId ����id
					 * @param categoryId ����id
					 */
					void addArticleCategory(@Param("articleId") Integer articleId, @Param("categoryId")  Integer categoryId);
				
					/**
					 * �����±�ǩ�����������
					 * @param articleId ����id
					 * @param tagId ��ǩid
					 */
					void addArticleTag(@Param("articleId")  Integer articleId, @Param("tagId")  Integer tagId);
					
	 7) ArticleMapper.xml �е�ʵ��
			  <!-- ��������,Ҫ�ǵ���Ҫ�������ɵ������� -->
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
				
				<!-- �����·������������� -->
				<insert id="addArticleCategory">
					insert into article_category_ref (article_id,category_id)  values (#{articleId}, #{categoryId}) 
				</insert>
				
				<!-- �����±�ǩ����������� -->
				<insert id="addArticleTag">
					insert into article_tag_ref (article_id,tag_id)  values (#{articleId}, #{tagId}) 
				</insert>
						
					
==== 2 ���봦��
		��web.xml������һ������������һ����������Ϳ���
		
		  <filter>
			    <filter-name>CharacterEncodingFilter</filter-name>
			    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
			    <!-- ���ù������е�����ֵ -->
			    <init-param>
			      <param-name>encoding</param-name>
			      <param-value>UTF-8</param-value>
			    </init-param>
			    <!-- ���������� -->
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
		  
==== 3 ���ı��༭��

		KindEditor�ǻ��������������������(WYSWYG)HTML�༭������ҪӦ����CMS����̳�����͵�WEB����� 
		��Ҫ�ص㣺 
		  1) �������٣����ܱȽ϶ࡣ 
		  2) ͨ�����plugin�ķ����������Զ��幦�ܡ� 
		  3) ����ɾ������Ҫ��plugin�������ļ���С�� 
		  4) ��������ı�༭����񣬺���վ��Ϊһ�塣 
		  5) ����������⣬��һ����ά�����ɿ��Ƶı༭��
		  
		  
		  1) ���� ,��ѹ ��  KindEditor ���Ƶ������е� resources Ŀ¼��
				
		  2) ������ص� css,js�ļ�
					<rapid:override name="frame-header-script">
						<link rel="stylesheet" href="resources/kindeditor/themes/default/default.css" />
						<link rel="stylesheet" href="resources/kindeditor/plugins/code/prettify.css" />
						<script charset="utf-8" src="resources/kindeditor/kindeditor-all-min.js"></script>
						<script charset="utf-8" src="resources/kindeditor/lang/zh-CN.js"></script>
						<script charset="utf-8" src="resources/kindeditor/plugins/code/prettify.js"></script>
					</rapid:override>
					 
		  3) ������Ⱦ
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
				
     4) �ѹ���ԭ���� layui �е�editor�Ķ���ɾ����
     
     
====4 ͼƬ�ϴ�������
   1) KindEditor �������ϴ�ͼƬ��·��
				
			<script>
				KindEditor.ready(function(K) {
					var editor1 = K.create('textarea[id="content"]', {
						allowFileManager : true,	
						width:"1100px",
						height:"420px",
						uploadJson : "article/uploadImg",   //ָ�����һ������˵�ַ,����ͼƬ�ϴ�
					});
					prettyPrint();
				});
			</script>
			
	2) ��springmvc.xml �н�������
	    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	       <!--���ߴ�Ϊ50MB-->
	    	<property name="maxUploadSize" value="52428800" />
	    	<property name="defaultEncoding" value="UTF-8" />
	    </bean>
	    
	3) ������ص�����
      <!-- �ļ��ϴ� -->
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
      
  4) ��tomcat����������·��
         ��tomcat�� server.xml ������������
	       <Host>
	         ....
	         <Context docBase="D:/imgupload/" path="/upload"  reloadable="true"  />
	       </Host>
	       
	      ����õ���sts ,��Ҫ�� Servers�����н�������
	      
	      �������Ժ�,������ D:/imgupload/�зż���ͼƬ,Ȼ������������,���������ַ����ֱ�ӷ��ʲ���
	      
	      http://localhost:8080/upload/mice.jpg  �ܿ���ͼƬ,֤�����óɹ���
	      
	 5) ͼƬ�ϴ����Ʋ�
				/**
				 * ���ı��༭����Ӧ��ͼƬ�ϴ�
				 * @return ���ı��༭��Ҫ���json���͵�����
				 * @throws IOException 
				 * @throws IllegalStateException 
				 */
				@ResponseBody @RequestMapping("/uploadImg")
				public String uploadArticleImg(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
					//�õ��ͻ��˴�������ͼƬ , imgFile ��һ���̶�����
					MultipartFile file= request.getFile("imgFile");
					
					//�������һ���ļ���
					String fileName=UUID.randomUUID().toString()+".jpg";
					
					//����һ������ļ���Ŀ��
					File destFile=new File("d:/imgupload/"+fileName);
					
					//���ļ��浽ĳ��Ŀ¼��
					file.transferTo(destFile);
			
					String path="http://localhost:8080/upload/"+fileName;
					
					//ע��,��� JSONObject ����Դ�� hutool ���߰�
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", path);
			
					return  obj.toString();
				}
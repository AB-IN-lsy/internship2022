======================================Lesson10 ===========================================
1 git 中的文件比较
2 git的版本回退, 和checkout 命令
3 github 的使用
4 码云的使用
5 git中的分支管理
6 在eclise中使用git
7 git 团队协作与代码冲突
==========================================================================================
==== 1 git 中的文件比较
	  1) 关于git中的中文文件名显示
		  默认情况下,用git管理的文件名如果是中间,在使用命令的时候它显示的是类似下面这样:
		    "\346\246\202\350\246\201\350\256\276\350\256\241\350\257\264\346\230\216\344\271\246.txt"
		  
		  可以设置:
		   > git config --global core.quotepath false
		   
	   2) 文件比较
	     git diff 命令 ,用于比较当前工作区中文件的的内容,和以往提交的内容有什么不同
	     
	      //例 建一个叫诗.txt的文件,写入下面的内容
						在遥远的西世界
						有一个川建国
						他是一位中国地下党员
						有一天他挂了
						死得其所...	
        >git add .
        >git commit -m"提交了一首诗"
        
        提交以后,对上面的文档进行修改
						在遥远的西世界
						有一个拜振华
						他是一位中国地下党员
						有一天他挂了
						死得其所...
						因为他去了乌克兰...不死才怪
       
					      
					> git diff
					diff --git a/词.txt b/词.txt
					index 59a937e..fe0e7cd 100644
					--- a/词.txt
					+++ b/词.txt
					@@ -1,5 +1,6 @@
					
					 在遥远的西世界
					-有一个川建国
					+有一个拜振华
					 他是一位中国地下党员
					 有一天他挂了
					-死得其所...
					\ No newline at end of file
					+死得其所...
					+因为他去了乌克兰...不死才怪
					\ No newline at end of file
					
==== 2 git的版本回退, 和checkout 命令
		 1) 撤销提交到暂存区的内容
		    >git reset  不加参数,表示撤销全部
		    >git reset a.txt  只撤销指定的文件
		
		 2) 历史版本的回退
		     >git reset --hard HEAD^ 回滚到上一次版本
		    
		     >git reset --hard b0c0f4e817ab2770586341694638d70537c6e3a8
		     >git reset --hard b0c0f 效果和上面相同,这个号可以写的短点
		     
		     >git reflog 可以看到提交和回退的历史
							02a906c (HEAD -> master) HEAD@{0}: reset: moving to 02a906c
							cf83bd6 HEAD@{1}: reset: moving to cf83bd683db795deae806271be731b73fa7d177c
							b0c0f4e HEAD@{2}: reset: moving to b0c0f4e817ab2770586341694638d70537c6e3a8
							0bb0ea5 HEAD@{3}: reset: moving to HEAD^
							cf83bd6 HEAD@{4}: commit: 小李修改了词
							0bb0ea5 HEAD@{5}: reset: moving to HEAD
							0bb0ea5 HEAD@{6}: commit: 小李写了一首词
							...
							
		 3) checkout 签出
		     >git checkout a.txt   从版本库中签出 a.txt 这个文件
		     >git checkout a.*   从版本库中签出所有以 a开头的文件
							
		 4) 删除版本库中的某个文件
		      先在工作目录中删除要删除的文件
		      >git add .
		      >git commit -m"小李删除了 a.html"


==== 3 github 的使用
		 官网  https://github.com
		 
			GitHub是一个面向开源及私有软件项目的托管平台，因为只支持 git 作为唯一的版本库格式进行托管，故名GitHub。
			GitHub于2008年4月10日正式上线，除了Git代码仓库托管及基本的 Web管理界面以外，
			还提供了订阅、讨论组、文本渲染、在线文件编辑器、协作图谱（报表）、代码片段分享（Gist）等功能。目前，
			其注册用户已经超过350万，托管版本数量也是非常之多，其中不乏知名开源项目 Ruby on Rails、jQuery、python 等。
			2018年6月4日，微软宣布，通过75亿美元的股票交易收购代码托管平台GitHub。
			
			1) 注册账号 (邮箱验证)
			2) 激活账号
			3) 设置个人信息
			4) 建个人的仓库
			5) 可以在仓库里建立或上传文件(代码等)
			6) 可以添加合作者 Collaborators
			      会给对方发一个邮件,发到对方邮箱里(不是github账号),对方要登录邮箱,同意一下才行
			      被邀请者,要先登录github
			      
			7) 如果仓库不用了,可以删除仓库
			
==== 4 码云的使用
     码云相当于中国的github, 码云也支持 SVN 
     
     https://gitee.com
     https://gitee.com/contrast  列出了码云和git本比的一些特点
     
		 社区版容量说明
		
			仓库数量 创建 1000 个仓库，不限制公私有。 
			仓库容量 单仓库大小上限为 500M 
			单文件最大 100M 
			用户总仓库容量为 5G 
			注：总仓库定义为用户名下以及所创建的组织下面的所有仓库。 
			附件容量 附件单文件大小上限为 100MB 
			单仓库附件总容量 1G 
			成员人数 公有仓库成员数量不限。 
			个人账号下所有私有仓库总的协作人数为 5 
			
		  码云可以和第三方账号绑定,绑定以后,就可以使用第三方账号登录码云
		  
		  在码云上创建一个仓库 (本例名为 black-shop) 创建过程略
		  
		  如何把本地仓库和远程仓库关联起来?
		  1) 在本地建一个目录名称任意 比如我的叫 xxx
		  2) 在目录上,右键, Git Bash Here
		  3) 执行 git init 
		  4) 用命令和远程仓库关联
		      > git remote add origin https://gitee.com/fangyiguo/black-shop.git     //origin相当于给远程仓库起了个名
		      > git remote -v 查看本地仓库和远程仓库的连接信
							origin  https://gitee.com/fangyiguo/black-shop.git (fetch)
							origin  https://gitee.com/fangyiguo/black-shop.git (push)
							
			5) git pull origin master	 //git pull 命令,用于从远程仓库拉取内容 origin 就是远程仓库地址的别名 , master 表示分支名
			     这个命令,可以直接简写成 git pull 
			     
			     在第一次执行的时候,出现了
			     fatal: couldn t find remote ref master, 原因是我们创建的git仓库中还没有任何内容,也不存在master分支,可以在git上创建个readme或其他文件,就可以了
			  
			  
			    再执行 > git pull origin master 
			    出现了一个错误 
			    fatal: refusing to merge unrelated histories
			    是因为本地仓库和远程仓库是两个独立的仓库, 我们可以采用强制合并的方式合并
			     >git pull origin master  --allow-unrelated-histories

			  	
			6) 在本地编写内容,往远程提交
			    >git push origin master
			    
			7) 如果想去掉和远程仓库的关联  
			    >git remote rm origin
			    
			    
			 在实际工作中,我们不要这么麻烦,可以直接克隆下来用就行
			    >git clone https://gitee.com/fangyiguo/black-shop.git 
			    
			    可以说,git clone直接解决了以下几个问题
			    =初始化了仓库
			    =直接建立本地仓库和远程仓库的关联
			    =把远程仓库的内容,下载到了本地
			    
==== 5 git中的分支管理
      1) 分支的查看
         >git branch
            * master   //默认只有master分支,前面带有 * 代表它是当前工作的分支
												
					> git branch -a  //-a这个参数表示显示所有分支,包括本地分支和远程分支
					* master
					  remotes/origin/HEAD -> origin/master
					  remotes/origin/master
					  
		  2) 创建分支
		     >git branch dev  //创建一个分支,叫dev
				 >git branch
					  dev
					* master
		  
		  3) 切换分支 
		    >git checkout dev  //切换到dev分支
		      Switched to branch 'dev'
		      
		    >git branch
					* dev
					  master
					  
			  也可以用一条命令,直接创建并切换到这个分支
			  > git checkout -b dev
			  
		 
		  4) 删除分支  
		    >git branch -d dev 
		     要注意,必须切换到其他分支上才能删除, 不能自已删除自已
		     这个分支只是删除远程分支
		     
		  5) 合并分支
		  		
		      创建一个叫dev的分支,并切换到这个分支,然后这个分支上做一些提交
		      >git branch dev;
		      >git checkout dev;
		      
		      >touch dev_a.txt;
		      >touch dev_b.txt;
		      >git add .
		      >git commit -m"小李在dev分支上添了两个文件"
		      
		      由于上面的操作是在dev分支上进行的,所以master分支上是看不到的
		      
		      >git checkout master 
		      >git log  //是不会看到dev分支上的提交的
		      
		      可以用命令,将dev分支,合并到master分支上
		      >git checkout master   合并前,要先切换到master分支上
		      >git merge dev  合并
							Updating f924177..cc8e2bf
							Fast-forward
							 dev_a.txt | 0
							 dev_b.txt | 0
							 2 files changed, 0 insertions(+), 0 deletions(-)
							 create mode 100644 dev_a.txt
							 create mode 100644 dev_b.txt

			6) 冲突的处理
			    创建一个新的分支 admindev,然后修改某个文件,比如修改 login.jsp, 并提交
			    >git branch admindev
			    >git checkout admindev
			    >vim login.jsp 进行一些修改 ...
			    >git add .
			    >git commit -m"admindev分支修改了 login.jsp"
			    
			    切换回master分支
			    >git checkout master
			    >vim login.jsp  ,也进行一些修改...
			    >git add .
			    >git commit -m "master 分支修改了 login.jsp"

				  进行合并
				  >git merge admindev
						Auto-merging login.jsp
						CONFLICT (content): Merge conflict in login.jsp    //conflict 是冲突的意思
						Automatic merge failed; fix conflicts and then commit the result.

			    
			      上面的提示,表示 login.jsp这个文档中,发生了冲突,我们打开这个文档,可以看到    
							<form>
							
							<<<<<<< HEAD
								<input type=text name="teacherName"><input name=password >
							=======
								<input type="text" name="stuName"><input name=password >
							>>>>>>> admindev
							</form>
					    
					    它把双方对这个文档内容的修改,都进行了标记和保留,我们的处理方式,手动把这个文档
					    改成我们想要的样子,比如
							<form>
								<input type=text name="stuTeacherName"><input name=password >
							</form>
											
						然后 
						 >git add .
						 >git commit -m"小张修正了冲突" 	
					 
		  7) 把本地的分支推到远程上
		       >git push origin  admindev
		       
		       这时我们再到 github 上去查看,就能看到刚才推上去的分支
		       
		       >git checkout admindev
		       
		       > 创建 .或修改一些文件 ...
		       > git add .
		       > git commit -m "admindev 添加了一些文件"
		       > git push origin admindev
		       
		      这时我们再到github上查看,可以看到 admindev 分支上推上来的内容
		       
		  
		  8) 在本地删除远程分支
					> git branch -a
					* admindev
					  dev
					  master
					  remotes/origin/HEAD -> origin/master
					  remotes/origin/admindev
					  remotes/origin/master

				  > git push origin -d  admindev
						remote: Powered by GITEE.COM [GNK-6.3]
						To https://gitee.com/fangyiguo/black-oa.git
						 - [deleted]         admindev


==== 6 在eclise中使用git
     见视频
     
     
==== 7 git 团队协作与代码冲突
		
		小张进行了一些修改之后
		
			>git add .
			>git commit -m"小张添加了内容"
			>git push
			
	  小李也进行了一些修改,然后
	    >git add .
	    >git commit -m "小李添加了内容"
	    >git push
	    
	   注意,小张和小李并没有修改相同的文件,所以目前还不存在冲突的问题
	   
	   但有下面的信息:
	   
				To https://gitee.com/fangyiguo/aolin-system.git
				 ! [rejected]        master -> master (fetch first)
				error: failed to push some refs to 'https://gitee.com/fangyiguo/aolin-system.git'
				hint: Updates were rejected because the remote contains work that you do
				hint: not have locally. This is usually caused by another repository pushing
				hint: to the same ref. You may want to first integrate the remote changes
				hint: (e.g., 'git pull ...') before pushing again.
				hint: See the 'Note about fast-forwards' in 'git push --help' for details.
    
      它的意思,是由于其他人对版本库进行了提交, 我们需要更新以后再进行push
     
     > git pull 
     输入上面的命令以后,它会进入vim编辑器,让我们编写备注信息,我们可以按具体的情况编写,保存
     
     然后再执行
     > git push
     
     
     冲突的发生:
       小张修改 pom.xml  ,比如修改了下面的内容
         <version>2.2.2-Final</version>
		
		   >git  add .
		   >git commit -m"小张改了版本"
		   >git pull
		   >git push
		   
		   小李也修改 pom.xml ,比如修改了下面的内容
		      <version>5.5.0-RELEASE</version>
		      
		   >git add .
		   >git commit -m"小李改了版本"
		   >git push
						To https://gitee.com/fangyiguo/aolin-system.git
						 ! [rejected]        master -> master (fetch first)
						error: failed to push some refs to 'https://gitee.com/fangyiguo/aolin-system.git'
						hint: Updates were rejected because the remote contains work that you do
						hint: not have locally. This is usually caused by another repository pushing
						hint: to the same ref. You may want to first integrate the remote changes
						hint: (e.g., 'git pull ...') before pushing again.
						hint: See the 'Note about fast-forwards' in 'git push --help' for details.
						
						它是告诉我们要先进行 git pull
						
		   >git pull
					error: Pulling is not possible because you have unmerged files.
					hint: Fix them up in the work tree, and then use 'git add/rm <file>'
					hint: as appropriate to mark resolution and make a commit.
					fatal: Exiting because of an unresolved conflict.
					
			  上面的意思就是指发生了冲突,我们要进行修正冲突,我们可以编辑pom.xml 文件,把冲空的地方处理掉
			  
			  处理完以后,git add ,git commit ,git push 就可以了
					
					
							
							
					
																										
																												  
														
								
								
									
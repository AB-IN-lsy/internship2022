======================================Lesson10 ===========================================
1 git �е��ļ��Ƚ�
2 git�İ汾����, ��checkout ����
3 github ��ʹ��
4 ���Ƶ�ʹ��
5 git�еķ�֧����
6 ��eclise��ʹ��git
7 git �Ŷ�Э��������ͻ
==========================================================================================
==== 1 git �е��ļ��Ƚ�
	  1) ����git�е������ļ�����ʾ
		  Ĭ�������,��git������ļ���������м�,��ʹ�������ʱ������ʾ����������������:
		    "\346\246\202\350\246\201\350\256\276\350\256\241\350\257\264\346\230\216\344\271\246.txt"
		  
		  ��������:
		   > git config --global core.quotepath false
		   
	   2) �ļ��Ƚ�
	     git diff ���� ,���ڱȽϵ�ǰ���������ļ��ĵ�����,�������ύ��������ʲô��ͬ
	     
	      //�� ��һ����ʫ.txt���ļ�,д�����������
						��ңԶ��������
						��һ��������
						����һλ�й����µ�Ա
						��һ��������
						��������...	
        >git add .
        >git commit -m"�ύ��һ��ʫ"
        
        �ύ�Ժ�,��������ĵ������޸�
						��ңԶ��������
						��һ������
						����һλ�й����µ�Ա
						��һ��������
						��������...
						��Ϊ��ȥ���ڿ���...�����Ź�
       
					      
					> git diff
					diff --git a/��.txt b/��.txt
					index 59a937e..fe0e7cd 100644
					--- a/��.txt
					+++ b/��.txt
					@@ -1,5 +1,6 @@
					
					 ��ңԶ��������
					-��һ��������
					+��һ������
					 ����һλ�й����µ�Ա
					 ��һ��������
					-��������...
					\ No newline at end of file
					+��������...
					+��Ϊ��ȥ���ڿ���...�����Ź�
					\ No newline at end of file
					
==== 2 git�İ汾����, ��checkout ����
		 1) �����ύ���ݴ���������
		    >git reset  ���Ӳ���,��ʾ����ȫ��
		    >git reset a.txt  ֻ����ָ�����ļ�
		
		 2) ��ʷ�汾�Ļ���
		     >git reset --hard HEAD^ �ع�����һ�ΰ汾
		    
		     >git reset --hard b0c0f4e817ab2770586341694638d70537c6e3a8
		     >git reset --hard b0c0f Ч����������ͬ,����ſ���д�Ķ̵�
		     
		     >git reflog ���Կ����ύ�ͻ��˵���ʷ
							02a906c (HEAD -> master) HEAD@{0}: reset: moving to 02a906c
							cf83bd6 HEAD@{1}: reset: moving to cf83bd683db795deae806271be731b73fa7d177c
							b0c0f4e HEAD@{2}: reset: moving to b0c0f4e817ab2770586341694638d70537c6e3a8
							0bb0ea5 HEAD@{3}: reset: moving to HEAD^
							cf83bd6 HEAD@{4}: commit: С���޸��˴�
							0bb0ea5 HEAD@{5}: reset: moving to HEAD
							0bb0ea5 HEAD@{6}: commit: С��д��һ�״�
							...
							
		 3) checkout ǩ��
		     >git checkout a.txt   �Ӱ汾����ǩ�� a.txt ����ļ�
		     >git checkout a.*   �Ӱ汾����ǩ�������� a��ͷ���ļ�
							
		 4) ɾ���汾���е�ĳ���ļ�
		      ���ڹ���Ŀ¼��ɾ��Ҫɾ�����ļ�
		      >git add .
		      >git commit -m"С��ɾ���� a.html"


==== 3 github ��ʹ��
		 ����  https://github.com
		 
			GitHub��һ������Դ��˽�������Ŀ���й�ƽ̨����Ϊֻ֧�� git ��ΪΨһ�İ汾���ʽ�����йܣ�����GitHub��
			GitHub��2008��4��10����ʽ���ߣ�����Git����ֿ��йܼ������� Web����������⣬
			���ṩ�˶��ġ������顢�ı���Ⱦ�������ļ��༭����Э��ͼ�ף�����������Ƭ�η���Gist���ȹ��ܡ�Ŀǰ��
			��ע���û��Ѿ�����350���йܰ汾����Ҳ�Ƿǳ�֮�࣬���в���֪����Դ��Ŀ Ruby on Rails��jQuery��python �ȡ�
			2018��6��4�գ�΢��������ͨ��75����Ԫ�Ĺ�Ʊ�����չ������й�ƽ̨GitHub��
			
			1) ע���˺� (������֤)
			2) �����˺�
			3) ���ø�����Ϣ
			4) �����˵Ĳֿ�
			5) �����ڲֿ��ｨ�����ϴ��ļ�(�����)
			6) ������Ӻ����� Collaborators
			      ����Է���һ���ʼ�,�����Է�������(����github�˺�),�Է�Ҫ��¼����,ͬ��һ�²���
			      ��������,Ҫ�ȵ�¼github
			      
			7) ����ֿⲻ����,����ɾ���ֿ�
			
==== 4 ���Ƶ�ʹ��
     �����൱���й���github, ����Ҳ֧�� SVN 
     
     https://gitee.com
     https://gitee.com/contrast  �г������ƺ�git���ȵ�һЩ�ص�
     
		 ����������˵��
		
			�ֿ����� ���� 1000 ���ֿ⣬�����ƹ�˽�С� 
			�ֿ����� ���ֿ��С����Ϊ 500M 
			���ļ���� 100M 
			�û��ֿܲ�����Ϊ 5G 
			ע���ֿܲⶨ��Ϊ�û������Լ�����������֯��������вֿ⡣ 
			�������� �������ļ���С����Ϊ 100MB 
			���ֿ⸽�������� 1G 
			��Ա���� ���вֿ��Ա�������ޡ� 
			�����˺�������˽�вֿ��ܵ�Э������Ϊ 5 
			
		  ���ƿ��Ժ͵������˺Ű�,���Ժ�,�Ϳ���ʹ�õ������˺ŵ�¼����
		  
		  �������ϴ���һ���ֿ� (������Ϊ black-shop) ����������
		  
		  ��ΰѱ��زֿ��Զ�ֿ̲��������?
		  1) �ڱ��ؽ�һ��Ŀ¼�������� �����ҵĽ� xxx
		  2) ��Ŀ¼��,�Ҽ�, Git Bash Here
		  3) ִ�� git init 
		  4) �������Զ�ֿ̲����
		      > git remote add origin https://gitee.com/fangyiguo/black-shop.git     //origin�൱�ڸ�Զ�ֿ̲����˸���
		      > git remote -v �鿴���زֿ��Զ�ֿ̲��������
							origin  https://gitee.com/fangyiguo/black-shop.git (fetch)
							origin  https://gitee.com/fangyiguo/black-shop.git (push)
							
			5) git pull origin master	 //git pull ����,���ڴ�Զ�ֿ̲���ȡ���� origin ����Զ�ֿ̲��ַ�ı��� , master ��ʾ��֧��
			     �������,����ֱ�Ӽ�д�� git pull 
			     
			     �ڵ�һ��ִ�е�ʱ��,������
			     fatal: couldn t find remote ref master, ԭ�������Ǵ�����git�ֿ��л�û���κ�����,Ҳ������master��֧,������git�ϴ�����readme�������ļ�,�Ϳ�����
			  
			  
			    ��ִ�� > git pull origin master 
			    ������һ������ 
			    fatal: refusing to merge unrelated histories
			    ����Ϊ���زֿ��Զ�ֿ̲������������Ĳֿ�, ���ǿ��Բ���ǿ�ƺϲ��ķ�ʽ�ϲ�
			     >git pull origin master  --allow-unrelated-histories

			  	
			6) �ڱ��ر�д����,��Զ���ύ
			    >git push origin master
			    
			7) �����ȥ����Զ�ֿ̲�Ĺ���  
			    >git remote rm origin
			    
			    
			 ��ʵ�ʹ�����,���ǲ�Ҫ��ô�鷳,����ֱ�ӿ�¡�����þ���
			    >git clone https://gitee.com/fangyiguo/black-shop.git 
			    
			    ����˵,git cloneֱ�ӽ�������¼�������
			    =��ʼ���˲ֿ�
			    =ֱ�ӽ������زֿ��Զ�ֿ̲�Ĺ���
			    =��Զ�ֿ̲������,���ص��˱���
			    
==== 5 git�еķ�֧����
      1) ��֧�Ĳ鿴
         >git branch
            * master   //Ĭ��ֻ��master��֧,ǰ����� * �������ǵ�ǰ�����ķ�֧
												
					> git branch -a  //-a���������ʾ��ʾ���з�֧,�������ط�֧��Զ�̷�֧
					* master
					  remotes/origin/HEAD -> origin/master
					  remotes/origin/master
					  
		  2) ������֧
		     >git branch dev  //����һ����֧,��dev
				 >git branch
					  dev
					* master
		  
		  3) �л���֧ 
		    >git checkout dev  //�л���dev��֧
		      Switched to branch 'dev'
		      
		    >git branch
					* dev
					  master
					  
			  Ҳ������һ������,ֱ�Ӵ������л��������֧
			  > git checkout -b dev
			  
		 
		  4) ɾ����֧  
		    >git branch -d dev 
		     Ҫע��,�����л���������֧�ϲ���ɾ��, ��������ɾ������
		     �����ֻ֧��ɾ��Զ�̷�֧
		     
		  5) �ϲ���֧
		  		
		      ����һ����dev�ķ�֧,���л��������֧,Ȼ�������֧����һЩ�ύ
		      >git branch dev;
		      >git checkout dev;
		      
		      >touch dev_a.txt;
		      >touch dev_b.txt;
		      >git add .
		      >git commit -m"С����dev��֧�����������ļ�"
		      
		      ��������Ĳ�������dev��֧�Ͻ��е�,����master��֧���ǿ�������
		      
		      >git checkout master 
		      >git log  //�ǲ��ῴ��dev��֧�ϵ��ύ��
		      
		      ����������,��dev��֧,�ϲ���master��֧��
		      >git checkout master   �ϲ�ǰ,Ҫ���л���master��֧��
		      >git merge dev  �ϲ�
							Updating f924177..cc8e2bf
							Fast-forward
							 dev_a.txt | 0
							 dev_b.txt | 0
							 2 files changed, 0 insertions(+), 0 deletions(-)
							 create mode 100644 dev_a.txt
							 create mode 100644 dev_b.txt

			6) ��ͻ�Ĵ���
			    ����һ���µķ�֧ admindev,Ȼ���޸�ĳ���ļ�,�����޸� login.jsp, ���ύ
			    >git branch admindev
			    >git checkout admindev
			    >vim login.jsp ����һЩ�޸� ...
			    >git add .
			    >git commit -m"admindev��֧�޸��� login.jsp"
			    
			    �л���master��֧
			    >git checkout master
			    >vim login.jsp  ,Ҳ����һЩ�޸�...
			    >git add .
			    >git commit -m "master ��֧�޸��� login.jsp"

				  ���кϲ�
				  >git merge admindev
						Auto-merging login.jsp
						CONFLICT (content): Merge conflict in login.jsp    //conflict �ǳ�ͻ����˼
						Automatic merge failed; fix conflicts and then commit the result.

			    
			      �������ʾ,��ʾ login.jsp����ĵ���,�����˳�ͻ,���Ǵ�����ĵ�,���Կ���    
							<form>
							
							<<<<<<< HEAD
								<input type=text name="teacherName"><input name=password >
							=======
								<input type="text" name="stuName"><input name=password >
							>>>>>>> admindev
							</form>
					    
					    ����˫��������ĵ����ݵ��޸�,�������˱�Ǻͱ���,���ǵĴ���ʽ,�ֶ�������ĵ�
					    �ĳ�������Ҫ������,����
							<form>
								<input type=text name="stuTeacherName"><input name=password >
							</form>
											
						Ȼ�� 
						 >git add .
						 >git commit -m"С�������˳�ͻ" 	
					 
		  7) �ѱ��صķ�֧�Ƶ�Զ����
		       >git push origin  admindev
		       
		       ��ʱ�����ٵ� github ��ȥ�鿴,���ܿ����ղ�����ȥ�ķ�֧
		       
		       >git checkout admindev
		       
		       > ���� .���޸�һЩ�ļ� ...
		       > git add .
		       > git commit -m "admindev �����һЩ�ļ�"
		       > git push origin admindev
		       
		      ��ʱ�����ٵ�github�ϲ鿴,���Կ��� admindev ��֧��������������
		       
		  
		  8) �ڱ���ɾ��Զ�̷�֧
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


==== 6 ��eclise��ʹ��git
     ����Ƶ
     
     
==== 7 git �Ŷ�Э��������ͻ
		
		С�Ž�����һЩ�޸�֮��
		
			>git add .
			>git commit -m"С�����������"
			>git push
			
	  С��Ҳ������һЩ�޸�,Ȼ��
	    >git add .
	    >git commit -m "С�����������"
	    >git push
	    
	   ע��,С�ź�С�û���޸���ͬ���ļ�,����Ŀǰ�������ڳ�ͻ������
	   
	   �����������Ϣ:
	   
				To https://gitee.com/fangyiguo/aolin-system.git
				 ! [rejected]        master -> master (fetch first)
				error: failed to push some refs to 'https://gitee.com/fangyiguo/aolin-system.git'
				hint: Updates were rejected because the remote contains work that you do
				hint: not have locally. This is usually caused by another repository pushing
				hint: to the same ref. You may want to first integrate the remote changes
				hint: (e.g., 'git pull ...') before pushing again.
				hint: See the 'Note about fast-forwards' in 'git push --help' for details.
    
      ������˼,�����������˶԰汾��������ύ, ������Ҫ�����Ժ��ٽ���push
     
     > git pull 
     ��������������Ժ�,�������vim�༭��,�����Ǳ�д��ע��Ϣ,���ǿ��԰�����������д,����
     
     Ȼ����ִ��
     > git push
     
     
     ��ͻ�ķ���:
       С���޸� pom.xml  ,�����޸������������
         <version>2.2.2-Final</version>
		
		   >git  add .
		   >git commit -m"С�Ÿ��˰汾"
		   >git pull
		   >git push
		   
		   С��Ҳ�޸� pom.xml ,�����޸������������
		      <version>5.5.0-RELEASE</version>
		      
		   >git add .
		   >git commit -m"С����˰汾"
		   >git push
						To https://gitee.com/fangyiguo/aolin-system.git
						 ! [rejected]        master -> master (fetch first)
						error: failed to push some refs to 'https://gitee.com/fangyiguo/aolin-system.git'
						hint: Updates were rejected because the remote contains work that you do
						hint: not have locally. This is usually caused by another repository pushing
						hint: to the same ref. You may want to first integrate the remote changes
						hint: (e.g., 'git pull ...') before pushing again.
						hint: See the 'Note about fast-forwards' in 'git push --help' for details.
						
						���Ǹ�������Ҫ�Ƚ��� git pull
						
		   >git pull
					error: Pulling is not possible because you have unmerged files.
					hint: Fix them up in the work tree, and then use 'git add/rm <file>'
					hint: as appropriate to mark resolution and make a commit.
					fatal: Exiting because of an unresolved conflict.
					
			  �������˼����ָ�����˳�ͻ,����Ҫ����������ͻ,���ǿ��Ա༭pom.xml �ļ�,�ѳ�յĵط������
			  
			  �������Ժ�,git add ,git commit ,git push �Ϳ�����
					
					
							
							
					
																										
																												  
														
								
								
									
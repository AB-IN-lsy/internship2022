========================================Lesson9 ===========================================
1 git���
2 git�İ�װ
3 git bash������
4 �ֿ�Ĵ������ύ
5 ��git�к����ļ�
===========================================================================================
==== 1 git���
  1)  �汾�⣨version control system��:
      �汾����ϵͳ��VCS����һ�ּ�¼һ���������ļ����ݱ仯���Ա㽫�������ض��汾�޶������ϵͳ��
      ʹ�ð汾����ϵͳͨ������ζ�ţ���������һ����������Ŀ�е��ļ��ĵĸ�ɾ��ɾ����Ҳ����������
      �ɻָ���ԭ�ȵ����ӡ� ���������ӵĹ�����ȴ΢����΢�������İ汾�� SVN ,GIT ,VSS 
      
  2) ����ʽ�汾��ͷֲ�ʽ�İ汾�� 
       (1) ����ʽ:��SVN,CVS�� 
		   		 ��һ�����й����ķ����������������ļ����޶��汾����Эͬ���������Ƕ�ͨ���ͻ������ӵ�
		   		 ��̨����������ȡ���µ��ļ������ύ����. �������������ѳ�Ϊ�汾����ϵͳ�ı�׼������

						�ô�:
						1�� �߼���ȷ������һ����˼άϰ�ߡ� 
						2�� ���ڹ���������ʽ���������ܱ�֤��ȫ�ԡ�
						3�� ����һ���Էǳ��ߡ�
						4�� �ʺϿ��������������Ŀ����
						
		       ȱ��:
			      ������ϡ� 
			      ����������ݿ����ڵ�λ�÷����𻵣���û����ǡ�����ݣ� �㽫��ʧ�������ݡ���������Ŀ����
			      �������ʷ��ֻʣ�������ڸ��Ի����ϱ����ĵ������ա�  
			      

			 (2) �ֲ�ʽ�汾����ϵͳ:�� GIT
		   			�ͻ��˲���ֻ��ȡ���°汾���ļ����գ����ǰѴ���ֿ������ؾ��������� 
		   			�κ�һ��Эͬ�����õķ������������ϣ��º󶼿������κ�һ����������ı��زֿ�ָ�����Ϊÿ
		   			һ�εĿ�¡������ʵ���϶���һ�ζԴ���ֿ����������.
		
						�ŵ�:
								1���ʺϷֲ�ʽ������ǿ�����塣 
								2������������ѹ����������������̫��
								3���ٶȿ졢��
								4����������������֮����Ժ����׵Ľ����ͻ��
								5�����߹�����
				
				   ȱ��:	
								1��ѧϰ������Զ��ԱȽϳ��� 
								2�������ϳ���˼ά��
								3�����뱣���Բһ�������߰��������¡�����Ϳ�����ȫ�������д���Ͱ汾��Ϣ��
								
								
   3) GIT
				 ��Դ�ķֲ�ʽ�汾����ϵͳ����C���Ա�д
				 Git �������İ汾���ƹ��� CVS, SVN��VSS��ȣ��������˷ֲ�ʽ�汾��ķ�ʽ�����������ύ���룬
				 ���뱣���ڱ��أ���������SVN�����ܽ��е�һϵ�в������������Ҫ���͵�������ֻ��Ҫpushһ�¼��ɣ�
				 ÿ���˶���һ�������Ĳֿ⡣
				 
					1��Git �Ƿֲ�ʽ�ģ�SVN ���ǡ�
					2��Git �����ݰ�Ԫ���ݷ�ʽ�洢���� SVN �ǰ��ļ�
					3��Git ��֧�� SVN �ķ�֧��ͬ����֧�� SVN ��ʵ���ǰ汾���е�����һ��Ŀ¼��
					4��Git û��һ��ȫ�ֵİ汾�ţ��� SVN ��
					5��Git ������������Ҫ���� SVN��Git �����ݴ洢ʹ�õ��� SHA-1 ��ϣ�㷨��


==== 2 git�İ�װ
		 git����
		   https://git-scm.com 
		   
      ��װ���� Git-2.24.1.2-64-bit.exe
      
      ʵ����,�ڰ�װ�����н��е�һЩѡ��,�� D:\Program Files\Git\etc\gitconfig �ļ���Ҳ�ǿ��Խ����޸ĵ�
      
      ��git�����еķ�ʽ
      1) �Ҽ� ,�ڵ����˵��е�  Git Bash Here
      2) ֱ���������д���
      
==== 3 git bash������
		���԰�סctl�� ,����������,���������С
		������ tab �����в�ȫ
		������ ���¼�ͷ����ִ�й�������
		  >ls  //�г���ǰĿ¼�µ��ļ�
		  >ls -l  //���б��ķ�ʽ�г���ǰĿ¼�µ�����
		  >ll  //�൱�� ls -l
		  >ls -al  //�г���ǰĿ¼����������,���������ļ�
		  
		  >clear  //����
		  >touch  //�����ļ�
		  >pwd  //�鿴��ǰλ��
		  >cat  //�鿴�ļ�����
		  >rm -rf �ļ���  //ɾ��
		  
		  vim �ı��༭����ʹ��
		    1) vim �ļ��� ����ָ��ģʽ,�����ģʽ��,���ܽ��б༭
		    2) ���� i �ַ�,����༭ģʽ,���Խ��б༭
		    3) �༭����Ժ�,��esc,����ָ��ģʽ
		    4) �� :wq �����˳�
		        wq�Ǳ����˳�,  q! ��ʾǿ���˳�
		            
==== 4 �ֿ�Ĵ������ύ
		1) git ��ȫ������
		
	   	>git config --global user.name "cat"
	   	>git config --global user.email "732422364@qq.com"
	   	
	   	Ҫע�� user.name ��  user.email �ǹ̶�д��,��Ҫд��
	   	����û�����������,��Ҫ���ڱ�ʶ���ĸ��û�,�����ʽҪ����
	   	
	   	�������Ժ�,���������������鿴
	   	>git config --global -l 
	   	   	
	   	ʵ����,���������,�����û��ļ���(�ҵĵ����� C:\Users\Administrator ) �е� .gitconfig �ļ�����������������
	   	[user]
				name = cat
				email = 732422364@qq.com
				
		2) �����ֿ�,��ɰ汾�Ŀ���
		
				git�еĹ�����,�ݴ���,��ʷ��,Զ�ֿ̲�
				
				==������: ���ǵ�ǰ������Ŀ¼
				==�ݴ���: Ӣ�� stage ��index 
				==��ʷ��: �ŵľ��ǽ��ܹ����İ汾��
				==Զ�ֿ̲�: ���пͻ��˹����Ĳֿ�
				
				�ύ������
				������ => �ݴ��� =>��ʷ�� =>Զ�ֿ̲�
				
				���ص�����
				Զ�ֿ̲� => ������ 
				
			  (1) �������زֿ�
			      >git init 
			        ִ������Ժ�,���ڵ�ǰĿ¼������һ���� .git ������Ŀ¼
			        ǧ��Ҫɾ��,�ݴ���,��ʷ��,��������һЩ��Ϣ����������
			        
			  (2) ��д�ļ�,Ȼ���ύ���ݴ���
	   	      >touch a.java
	   	      >touch b.java
	   	      >touch a.html
	   	      >touch b.html
	   	      >git add a.html   //ֻ��a.html�ŵ��ݴ���
	   	      >git add *.html   //ֻ��������.html��β���ļ��ŵ��ݴ���
	   	      >git add .        //�����е��ļ����ŵ��ݴ���
	   	      >git status  //�鿴��Щ�ļ��������ݴ�������
	   	                   //���ֵ�,��û�����ӵ��ݴ�����,��ɫ�������ӵ��ݴ�����
	   	                   
	   	  (3) �ύ����ʷ��
	   	      >git commit -m"����Ҫ����һЩ˵����Ϣ"
	   	            -m �������������һЩ˵����Ϣ,��Ϣ��-m֮������пո�Ҳ����û��
	   	            
	   	            ����ύ��ʱ��,��ָ�� -m����,�������ύ��ʱ��Ĭ�ϴ�vim�༭��,
	   	            ���ǿ�������������ע���Ժ�,�ٽ����ύ
	   	            
	   	  (4) �鿴��ʷ���еİ汾
	   	      >git log �鿴�����汾
	   	      
	   	      >git log --pretty=oneline  �鿴�����汾,�Ծ���ķ�ʽ��ʾ  
								2759539caadfa3071ab97f68587782696cd81544 (HEAD -> master) 04  С����Ĵ��ύ,����������jsp�ļ�
								02a906ce0d7c14a585d411dcb457d84b44a508bc 03 С��������ύ,�����������ı��ļ�
								28f661932dc08b85fd6b39456bb6118b15cf4f76 02 С��ڶ����ύ �õ�vim��д����ʾ��Ϣ
								b0c0f4e817ab2770586341694638d70537c6e3a8 01 ����С��ĵ�һ���ύ
								
			  (5) ��ΰ��ύ���ݴ��������ݳ���
					  >git reset �ļ���  ��ĳ���ļ����ݴ����г���
					  >git reset  �����з����ݴ����е��ļ�������
								
==== 5 ��git�к����ļ�
      ��һЩ�ļ�,��Ȼ�����ǵĹ�������,�����ǲ�����������汾����
        ���Խ�һ���� .gitignore ���ļ�,�����в�����git�������ļ���Ŀ¼д������
        ��window�²���ֱ�Ӵ�������ļ�,���ǿ�����vim�༭��ֱ�Ӵ���
        ����
         .gitignore 
         ---------------------------------
					my_self_note.txt
					.settings
					target
					.classpath

									   	            
										
										  
										
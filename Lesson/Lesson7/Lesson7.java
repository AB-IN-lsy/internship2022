=======================================Lesson7 ===========================================
1 ��������
==========================================================================================
==== 1 ��������

   1) �һ������springmvc�ܹ�����Ŀ ,�ŵ�tomcat������������
   2) ���һ����¼���� 
   3) ���һ������������
         == ����û� (������ϴ�ͼƬ)
         == �û��б� (�������ʾ��ͼƬ)
                == �޸��û�
                == ɾ���û�
         
         == �˳���¼
   
   ��: ��Ӧ��ʵ����
	
		public class User {
		    private Integer userId;   //����,����id
		
		    private String userName;  //�˺�
		
		    private String userPass;  //����
		
		    private String userNickname;  //�ǳ�
		
		    private String userEmail;  //����
		
		    private String userUrl;  //�û���ַ
		
		    private String userAvatar;   //ͷ��ͼƬ��ַ
		
		    private String userLastLoginIp;  //����¼��ip��ַ
		
		    private Date userRegisterTime;  //ע��ʱ��  
		
		    private Date userLastLoginTime; //����¼ʱ��
		
		    private Integer userStatus;  //�û�״̬
		}
		
		������������,���ݿ����� dataTime ����,
	  ʵ�����е��ֶ�,��java.util.Date ���� 
		
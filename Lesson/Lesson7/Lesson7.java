=======================================Lesson7 ===========================================
1 今日任务
==========================================================================================
==== 1 今日任务

   1) 搭建一个基于springmvc架构的项目 ,放到tomcat容器中能运行
   2) 完成一个登录功能 
   3) 完成一个主界面的设计
         == 添加用户 (最好能上传图片)
         == 用户列表 (最好能显示出图片)
                == 修改用户
                == 删除用户
         
         == 退出登录
   
   附: 对应的实体类
	
		public class User {
		    private Integer userId;   //主键,自增id
		
		    private String userName;  //账号
		
		    private String userPass;  //密码
		
		    private String userNickname;  //昵称
		
		    private String userEmail;  //邮箱
		
		    private String userUrl;  //用户网址
		
		    private String userAvatar;   //头象图片地址
		
		    private String userLastLoginIp;  //最后登录的ip地址
		
		    private Date userRegisterTime;  //注册时间  
		
		    private Date userLastLoginTime; //最后登录时间
		
		    private Integer userStatus;  //用户状态
		}
		
		对于日期类型,数据库中用 dataTime 类型,
	  实体类中的字段,用java.util.Date 类型 
		
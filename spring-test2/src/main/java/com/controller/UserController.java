package com.controller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beans.User;
import com.mapper.UserMapper;

@Controller @RequestMapping("/user")
public class UserController {
	@RequestMapping("/login")
	public String login(String userName,String userPass, ModelMap m, HttpSession httpsession) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		
		User userLogin = new User();
		userLogin.setUserName(userName);
		userLogin.setUserPass(userPass);
		User user = userMapper.login(userLogin);
		
		if(user != null) {
			httpsession.setAttribute("session_userName", userName); //注意,这个是取session中的数据
			return "main";	   //物理视图 = 前缀+逻辑视图+后缀  /WEB-INF/view/main.jsp
		}
		else {
			m.put("msg","账号或密码错误,登录失败");
			return "forward:/login.jsp";   
		}		
	}
	
	@RequestMapping("/getAll")
	public ModelAndView getAll() throws IOException {

		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		
		List<User> userList = userMapper.getUserList();
		ModelAndView mv=new ModelAndView();
		mv.addObject("userList", userList); 
		mv.setViewName("user-manager");
		return mv;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)  //注意,它只处理GET请求
	public String goToAdd() {
		return "user-add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)  //注意,它只处理POST请求
	public String add(User user, MultipartFile uploadPhoto, HttpServletRequest request, ModelMap m) throws IOException {  //注意,这里直接用 pojo类型的参数
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		
		//得到这个目录所对应的物理路径(这个地址是在web服务器上)
		String realPath =request.getServletContext().getRealPath("/upload-files");
		File destFile=new File(realPath, uploadPhoto.getOriginalFilename());
		uploadPhoto.transferTo(destFile);
		System.out.println("photo.getContentType(): " + uploadPhoto.getContentType());
		System.out.println("photo.getName(): " + uploadPhoto.getName());  //得到上传字段的名称
		System.out.println("photo.getOriginalFilename(): " + uploadPhoto.getOriginalFilename());  //得到文件名称
		System.out.println("photo.getSize(): " + uploadPhoto.getSize());  //文件大小
		
		
		user.setUserPhoto(uploadPhoto.getOriginalFilename()); // 需要给photo设上名字
		int result = userMapper.add(user);
		System.out.println(user.getUserPhoto());
		System.out.println(result == 1 ? "添加成功，主键为" + user.getUserId() :"添加失败" );
		
		if(result == 1)
			m.put("msg","用户" + user.getUserId() + "添加成功");
		else
			m.put("msg","用户" + user.getUserId() + "添加失败");
		
		session.commit(); // 千万别忘了提交事务
		session.close();
	
	
		return "user-add";
	}

	@RequestMapping(value="/update/{userId}", method=RequestMethod.GET)
	public String goToUpdate(@PathVariable("userId") int userId, ModelMap m) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		
		User user= userMapper.getUserById(userId);
		m.put("user", user);
		return "user-update";		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("user") User user, ModelMap m) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
				
		int result = userMapper.update(user);
		System.out.println(result == 1 ? "更新成功" :"更新失败" );
		if(result == 1)
			m.put("msg","用户" + user.getUserId() + "修改成功");
		else
			m.put("msg","用户" + user.getUserId() + "修改失败");
		session.commit();
		session.close();
		return "user-update";
	} 
	
	// restfull风格
	@RequestMapping("/delete/{userId}")
	public String delete(@PathVariable("userId") int userId, ModelMap m) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		int result = userMapper.delete(userId);
		System.out.println(result == 1 ? "删除成功" :"删除失败" );
		
		if(result == 1)
			m.put("msg","用户" + userId + "删除成功");
		else
			m.put("msg","用户" + userId + "删除失败");
		
		session.commit();
		session.close();
		return "forward:/user/getAll";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//将session中的所有数据全清除
		session.invalidate();
		return "forward:/login.jsp";	
	}

}
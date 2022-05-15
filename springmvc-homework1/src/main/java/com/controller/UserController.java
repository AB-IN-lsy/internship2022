package com.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beans.User;
import com.mapper.UserMapper;

@Controller @RequestMapping("/user")
public class UserController {
	@RequestMapping("/login")
	public String login(String userName,String userPass, ModelMap m) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		
		User userLogin = new User();
		userLogin.setUserName(userName);
		userLogin.setUserPass(userPass);
		User user = userMapper.login(userLogin);
		
		if(user != null) {
			m.put("msg", "我超，登陆成功了爹");
			return "/main";	   //物理视图 = 前缀+逻辑视图+后缀  /WEB-INF/view/main.jsp
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
}
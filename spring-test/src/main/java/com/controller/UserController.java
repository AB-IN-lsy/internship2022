package com.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			httpsession.setAttribute("session_userName", userName); //ע��,�����ȡsession�е�����
			return "main";	   //������ͼ = ǰ׺+�߼���ͼ+��׺  /WEB-INF/view/main.jsp
		}
		else {
			m.put("msg","�˺Ż��������,��¼ʧ��");
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

	@RequestMapping(value="/add", method=RequestMethod.GET)  //ע��,��ֻ����GET����
	public String goToAdd() {
		return "user-add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)  //ע��,��ֻ����POST����
	public String add(User user, ModelMap m) throws IOException {  //ע��,����ֱ���� pojo���͵Ĳ���
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		
		int result = userMapper.add(user);
		System.out.println(result == 1 ? "��ӳɹ�������Ϊ" + user.getUserId() :"���ʧ��" );
		m.put("msg","�û���ӳɹ�");
		session.commit(); // ǧ��������ύ����
		session.close();
		return "user-add";
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String goToUpdate(int userId, ModelMap m) throws IOException {
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
				
		userMapper.update(user);
		m.put("msg","�û��޸ĳɹ���,����");
		
		session.commit();
		session.close();
		return "user-update";
	} 
	
	@RequestMapping("/delete")
	public String delete(Integer userId, ModelMap m) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		userMapper.delete(userId);	
		
		session.commit();
		session.close();
		return "forward:/user/getAll";
	}

	@RequestMapping(value="/test", method=RequestMethod.GET)  //ע��,��ֻ����GET����
	public String goToTest() {
		return "user-test";
	}

}
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
	public String add(User user, MultipartFile uploadPhoto, HttpServletRequest request, ModelMap m) throws IOException {  //ע��,����ֱ���� pojo���͵Ĳ���
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		
		//�õ����Ŀ¼����Ӧ������·��(�����ַ����web��������)
		String realPath =request.getServletContext().getRealPath("/upload-files");
		File destFile=new File(realPath, uploadPhoto.getOriginalFilename());
		uploadPhoto.transferTo(destFile);
		System.out.println("photo.getContentType(): " + uploadPhoto.getContentType());
		System.out.println("photo.getName(): " + uploadPhoto.getName());  //�õ��ϴ��ֶε�����
		System.out.println("photo.getOriginalFilename(): " + uploadPhoto.getOriginalFilename());  //�õ��ļ�����
		System.out.println("photo.getSize(): " + uploadPhoto.getSize());  //�ļ���С
		
		
		user.setUserPhoto(uploadPhoto.getOriginalFilename()); // ��Ҫ��photo��������
		int result = userMapper.add(user);
		System.out.println(user.getUserPhoto());
		System.out.println(result == 1 ? "��ӳɹ�������Ϊ" + user.getUserId() :"���ʧ��" );
		
		if(result == 1)
			m.put("msg","�û�" + user.getUserId() + "��ӳɹ�");
		else
			m.put("msg","�û�" + user.getUserId() + "���ʧ��");
		
		session.commit(); // ǧ��������ύ����
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
		System.out.println(result == 1 ? "���³ɹ�" :"����ʧ��" );
		if(result == 1)
			m.put("msg","�û�" + user.getUserId() + "�޸ĳɹ�");
		else
			m.put("msg","�û�" + user.getUserId() + "�޸�ʧ��");
		session.commit();
		session.close();
		return "user-update";
	} 
	
	// restfull���
	@RequestMapping("/delete/{userId}")
	public String delete(@PathVariable("userId") int userId, ModelMap m) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		UserMapper userMapper =session.getMapper(UserMapper.class);
		int result = userMapper.delete(userId);
		System.out.println(result == 1 ? "ɾ���ɹ�" :"ɾ��ʧ��" );
		
		if(result == 1)
			m.put("msg","�û�" + userId + "ɾ���ɹ�");
		else
			m.put("msg","�û�" + userId + "ɾ��ʧ��");
		
		session.commit();
		session.close();
		return "forward:/user/getAll";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//��session�е���������ȫ���
		session.invalidate();
		return "forward:/login.jsp";	
	}

}
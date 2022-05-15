package com.controller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beans.UserInfo;
import com.dao.UserDao;
@Controller @RequestMapping("/user")
public class UserController {
	UserDao dao=new UserDao();

	@RequestMapping(value="/login")
	public String login(String userName,String password, ModelMap m, Model model,Map map, HttpSession session) {
		if("admin".equals(userName) && "123".equals(password)) {
			 session.setAttribute("session_userName", userName);
			 
			 return "/main";	   //������ͼ = ǰ׺+�߼���ͼ+��׺  /WEB-INF/view/main.jsp
		}
		else {
			m.put("msg","�˺Ż��������,��¼ʧ��");
			model.addAttribute("info","���Ǹ��׳�,���û������ǲ�ס");
			map.put("str", "��Ҫ��,˭�������е����");
			
			return "forward:/login.jsp";   
		}		
	}	
	
	@RequestMapping("/getAll")
	public ModelAndView getAll() {
		List<UserInfo> userList= dao.getAll();		
		ModelAndView mv=new ModelAndView();
		mv.addObject("userList",userList); 
		mv.setViewName("user-manager");
		return mv;
	}

	//ת���û�����ҳ��,ֻ����GET����
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String gotoAdd() {
		return "user-add";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(UserInfo user, MultipartFile uploadphoto, HttpServletRequest request,  ModelMap m) throws IllegalStateException, IOException {
		user.setPhoto(uploadphoto.getOriginalFilename());
		dao.addUser(user);
		
		System.out.println("photo.getContentType(): " + uploadphoto.getContentType());
		System.out.println("photo.getName(): " + uploadphoto.getName());  //�õ��ϴ��ֶε�����
		System.out.println("photo.getOriginalFilename(): " + uploadphoto.getOriginalFilename());  //�õ��ļ�����
		System.out.println("photo.getSize(): " + uploadphoto.getSize());  //�ļ���С
		
		
		String realPath =request.getServletContext().getRealPath("/upload-files");
		System.out.println(realPath);
		
		File destFile=new File(realPath,uploadphoto.getOriginalFilename());
		
		uploadphoto.transferTo(destFile);

		m.put("msg","�û����ӳɹ�");	
		return "user-add";
	}

	@RequestMapping("/deleteUsers")
	public String deleteUsers( Integer [] ids, ModelMap m ) {
		dao.deleteUsers(ids);
		
		List<UserInfo> userList= dao.getAll();		
		m.put("userList",userList);
		return "user-manager";		
	}
	
	//�����û�id��ѯ�û���Ϣ,ת���û��޸�ҳ
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String goToUpdate(int id,ModelMap m) {
		UserInfo user= dao.getUserById(id);
		m.put("user",  user);
		return "user-update";		
	}
	
	/*�������ύ����,�޸��û���Ϣ
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(UserInfo user,ModelMap m) {
		dao.updateUser(user);
		
		//�ش�����,Ŀ����Ϊ�˻���
		m.put("user", user);
		m.put("msg","�û��޸ĳɹ�");
		return "user-update";
	} */
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("user") UserInfo user,ModelMap m) {
		dao.updateUser(user);
		m.put("msg","�û��޸ĳɹ���,����");
		return "user-update";
	} 
	
	@RequestMapping("/delete")
	public String  delete(Integer id,ModelMap m) {
		dao.deleteUser(id);
		
		/*
		List<UserInfo> userList=dao.getAll();
		m.put("userList", userList);
		return "user-manager"; 
		 */
		
		return "forward:/user/getAll";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//��session�е���������ȫ���
		session.invalidate();
		return "forward:/login.jsp";	
	}
	
	@RequestMapping("/del/{xxx}")
	public String del(@PathVariable("xxx") Integer id ) {
		dao.deleteUser(id);
		return "forward:/user/getAll";
	}
	
	@RequestMapping("test/{a}/{b}/{c}")
	public String test(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") int c) {
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		return "xxxxxx";
	}

}
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
			 
			 return "/main";	   //物理视图 = 前缀+逻辑视图+后缀  /WEB-INF/view/main.jsp
		}
		else {
			m.put("msg","账号或密码错误,登录失败");
			model.addAttribute("info","真是个白痴,连用户名都记不住");
			map.put("str", "不要紧,谁还不能有点错呢");
			
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

	//转向到用户添加页面,只处理GET请求
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String gotoAdd() {
		return "user-add";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(UserInfo user, MultipartFile uploadphoto, HttpServletRequest request,  ModelMap m) throws IllegalStateException, IOException {
		user.setPhoto(uploadphoto.getOriginalFilename());
		dao.addUser(user);
		
		System.out.println("photo.getContentType(): " + uploadphoto.getContentType());
		System.out.println("photo.getName(): " + uploadphoto.getName());  //得到上传字段的名称
		System.out.println("photo.getOriginalFilename(): " + uploadphoto.getOriginalFilename());  //得到文件名称
		System.out.println("photo.getSize(): " + uploadphoto.getSize());  //文件大小
		
		
		String realPath =request.getServletContext().getRealPath("/upload-files");
		System.out.println(realPath);
		
		File destFile=new File(realPath,uploadphoto.getOriginalFilename());
		
		uploadphoto.transferTo(destFile);

		m.put("msg","用户添加成功");	
		return "user-add";
	}

	@RequestMapping("/deleteUsers")
	public String deleteUsers( Integer [] ids, ModelMap m ) {
		dao.deleteUsers(ids);
		
		List<UserInfo> userList= dao.getAll();		
		m.put("userList",userList);
		return "user-manager";		
	}
	
	//根据用户id查询用户信息,转到用户修改页
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String goToUpdate(int id,ModelMap m) {
		UserInfo user= dao.getUserById(id);
		m.put("user",  user);
		return "user-update";		
	}
	
	/*真正的提交表单,修改用户信息
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(UserInfo user,ModelMap m) {
		dao.updateUser(user);
		
		//回传数据,目的是为了回显
		m.put("user", user);
		m.put("msg","用户修改成功");
		return "user-update";
	} */
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("user") UserInfo user,ModelMap m) {
		dao.updateUser(user);
		m.put("msg","用户修改成功了,哈哈");
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
		//将session中的所有数据全清除
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

package com.controller;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.beans.UserInfo;
import com.dao.UserDao;
@Controller @RequestMapping("/user")
public class UserController {
	UserDao dao=new UserDao();
	
	@RequestMapping(value="/login")
	public String login(String userName,String password, ModelMap m) {
		if("admin".equals(userName) && "123".equals(password)) {
			 return "/main";	   //ŒÔ¿Ì ”Õº = «∞◊∫+¬ﬂº≠ ”Õº+∫Û◊∫  /WEB-INF/view/main.jsp
		}
		else {
			m.put("msg","’À∫≈ªÚ√‹¬Î¥ÌŒÛ,µ«¬º ß∞‹");
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
}

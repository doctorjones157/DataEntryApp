package com.prod.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.prod.dataDao.LoginDao;
import com.prod.exception.UserNotFoundException;
import com.prod.model.User;

@Controller
public class LoginController {

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@GetMapping("/login")
	public String welcome(User user) {
		return "welcome";
	}
	
	@PostMapping("/loginuser")
	public String validateLogin(@Valid User user,HttpServletRequest req) {
		try {
			this.loginDao.validateUser(user);
			Map<String,HttpSession> userSession = new HashMap<String, HttpSession>();
			HttpSession session = req.getSession();
			userSession.put(user.getUserId(), session);
			
		return "redirect:/userhome";
		}catch (UserNotFoundException e) {
			return "redirect:/welcome";
		}
		
	}
}

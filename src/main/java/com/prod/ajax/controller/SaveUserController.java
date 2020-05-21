package com.prod.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prod.dataDao.LoginDao;
import com.prod.exception.UserAlreadyExistException;
import com.prod.model.User;

@RestController
public class SaveUserController {
	@Autowired
	private LoginDao loginDao;
	
	@PostMapping("/saveUser")
	public String saveUser(@RequestParam("userId") String userId,
							@RequestParam("userName") String userName,
							@RequestParam("password") String password,
							HttpServletResponse res) throws IOException {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		JSONObject obj = new JSONObject();
		try {
			loginDao.saveUser(user);
			obj.put("message", "User saved successfully");
		}catch (UserAlreadyExistException e) {
			obj.put("error", "UserId already exist");
		}
		catch (Exception e) {
			obj.put("error", "Error occurred");
		}
		PrintWriter pw = res.getWriter();
		obj.write(pw);
		pw.close();
		return null;
		
	}
	@GetMapping("/getAllUsers")
	public String getAllUsers(HttpServletResponse res) throws IOException {
		List<User> userList = loginDao.getAllUsers();
		JSONArray arr = new JSONArray();
		JSONObject userArrObj = new JSONObject();
		for (User user : userList) {
			JSONObject obj = new JSONObject();
			obj.put("userId", user.getUserId());
			obj.put("userName", user.getUserName());
			obj.put("password", "***");
			arr.put(obj);
		}
		userArrObj.put("userList", arr);
		PrintWriter pw = res.getWriter();
		userArrObj.write(pw);
		pw.close();
		return null;
	}
	@GetMapping("/editUser")
	public String editUser(@RequestParam("userId") String userId, HttpServletResponse res) throws IOException {
		User user = loginDao.getUserByUserId(userId);
		JSONObject obj = new JSONObject();
		obj.put("userId", user.getUserId());
		obj.put("userName", user.getUserName());
		obj.put("password", user.getPassword());
		PrintWriter pw = res.getWriter();
		obj.write(pw);
		pw.close();
		return null;
	}
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") String userId, HttpServletResponse res) throws IOException {
		JSONObject obj = new JSONObject();
		try {
		 loginDao.deleteUserByUserId(userId);
		 obj.put("message", "Deleted Successfully");
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "Error occurred");
		}
		 
		 
		PrintWriter pw = res.getWriter();
		obj.write(pw);
		pw.close();
		return null;
	}
	@PutMapping("/updateUser")
	public String updateUser(@RequestParam("userId") String userId,
							@RequestParam("userName") String userName,
							@RequestParam("password") String password,
							HttpServletResponse res) throws IOException {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		JSONObject obj = new JSONObject();
		try {
			loginDao.updateUser(user);
			obj.put("message", "User saved successfully");
		}catch (UserAlreadyExistException e) {
			obj.put("error", "UserId already exist");
		}
		catch (Exception e) {
			obj.put("error", "Error occurred");
		}
		PrintWriter pw = res.getWriter();
		obj.write(pw);
		pw.close();
		return null;
		
	}
}

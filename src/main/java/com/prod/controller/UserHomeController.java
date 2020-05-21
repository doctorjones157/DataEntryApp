package com.prod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserHomeController {

	@GetMapping("/userhome")
	public String userhome() {
		return "userhome";
		
	}
}

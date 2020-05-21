package com.prod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatewiseTransactionController {

	@GetMapping("/datewisetransactions")
	public String dateWiseTransaction(ModelMap model) {
		
		return "datewisetrans";
	}
}

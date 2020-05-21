package com.prod.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prod.dataDao.LoginDao;
import com.prod.dataDao.UserTransactionDao;
import com.prod.model.UserTransaction;

@Controller
public class UserTransactionController {
	
	@Autowired
	private LoginDao logindao;
	@Autowired
	private UserTransactionDao userTransactionDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@GetMapping("/userTransactions")
	private String getUserTransaction(Model model) {
		System.out.println("==usertrans==");
		UserTransaction transaction = new UserTransaction();
		IntStream.range(0, 2).forEach(a->{
			UserTransaction u = new UserTransaction();
			transaction.getTransactionList().add(u);
		});
		System.out.println(transaction.getTransactionList().size()+"==size=");
		model.addAttribute("userTransaction",transaction);
		model.addAttribute("userList",logindao.getAllUsers());
		return "usertransactions";
	}
	@PostMapping("/userTransactionsAction")
	private String addMore(@ModelAttribute UserTransaction userTransaction
						,Model model
						,@RequestParam("flag") String param 
						) {
		System.out.println("==>"+userTransaction.getTransactionList().size()+"====>");
		if(param != null && param.equals("add")) {
			IntStream.range(0, 1).forEach(a->{
				UserTransaction u = new UserTransaction();
				userTransaction.getTransactionList().add(u);
			});
		}else if(param != null && param.equals("save")) {
			this.userTransactionDao.saveTransaction(userTransaction);
		}else if(param != null && param.equals("inputChange")) {
			List<UserTransaction> transactionList =  this.userTransactionDao.getSavedTransactionList(userTransaction);
			System.out.println(userTransaction.getTransactionList().size()+"==="+transactionList.size());
			if(transactionList.size() > 0) {
				userTransaction.setTransactionList(transactionList);
			}else if(userTransaction.getTransactionList().size() == 0){
				IntStream.range(0, 1).forEach(a->{
					UserTransaction u = new UserTransaction();
					userTransaction.getTransactionList().add(u);
				});
			}else {
				List<UserTransaction> transList = new ArrayList<UserTransaction>();
				IntStream.range(0, 2).forEach(a->{
					UserTransaction u = new UserTransaction();
					transList.add(u);
				});
				userTransaction.setTransactionList(transList);
			}
				
		}
		
		System.out.println("userTransaction => "+userTransaction.getTransDate());
		model.addAttribute("userTransaction",userTransaction);
		model.addAttribute("userList",logindao.getAllUsers());
		return "usertransactions";
	}
}

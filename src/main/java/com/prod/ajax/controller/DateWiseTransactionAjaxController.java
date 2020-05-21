package com.prod.ajax.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prod.dataDao.DateWiseTransactionDao;
import com.prod.model.UserTransaction;

@RestController
public class DateWiseTransactionAjaxController {

	@Autowired
	private DateWiseTransactionDao dateWiseTransactionDao;
	@GetMapping("/getTransactions")
	public void getTransactionbyDates(@RequestParam String fromDate,
										@RequestParam String toDate
										,HttpServletResponse res) {
		
		List<UserTransaction> transList = this.dateWiseTransactionDao.getTransactionBydate(fromDate, toDate);
		System.out.println(transList.size());
	}
}

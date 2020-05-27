package com.prod.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
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
										,HttpServletResponse res) throws IOException {
		
		List<UserTransaction> transList = this.dateWiseTransactionDao.getTransactionBydate(fromDate, toDate);
		
		Map<String, Double> map = transList.stream().collect(Collectors.groupingBy(UserTransaction::getUserId,
															 Collectors.summingDouble(UserTransaction::getWeight)));
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		map.forEach((k,v)->{
			String userName = dateWiseTransactionDao.getUserNameById(k);

			JSONObject obj = new JSONObject();
			obj.put("userName", userName);
			obj.put("userId", k);
			obj.put("weight", v);
			array.put(obj);
		});
		object.put("userList", array);
		PrintWriter pw = res.getWriter();
		object.write(pw);
		pw.close();
		
	}
	@GetMapping("/getAllTransactionsByUserId")
	public void getAllTransactionsByUserId(@RequestParam String fromDate,
										@RequestParam String toDate,
										@RequestParam String userId
										,HttpServletResponse res) throws IOException {
		
		List<UserTransaction> newTransList = new ArrayList<UserTransaction>();
		List<UserTransaction> transList = this.dateWiseTransactionDao.getTransactionByDateAndUserId(fromDate, toDate,userId);
		transList.forEach( a->{newTransList.add(a);});
		newTransList.sort(Comparator.comparing(UserTransaction::getTransDate));
		
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		
		newTransList.forEach(a->{
		
		JSONObject obj = new JSONObject();
		obj.put("transDate", a.getTransDate());
		obj.put("weight", a.getWeight());
		obj.put("userId", a.getUserId());
		array.put(obj);
		});
		object.put("userList", array);
		PrintWriter pw = res.getWriter();
		object.write(pw);
		pw.close();

		
	}
}

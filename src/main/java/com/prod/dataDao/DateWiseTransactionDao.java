package com.prod.dataDao;

import java.util.List;

import com.prod.model.UserTransaction;

public interface DateWiseTransactionDao {
	List<UserTransaction> getTransactionBydate(String fromDate, String toDate);

	String getUserNameById(String k);

	List<UserTransaction> getTransactionByDateAndUserId(String fromDate, String toDate, String userId);
		
	
}

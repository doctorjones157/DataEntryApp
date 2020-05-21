package com.prod.dataDao;

import java.util.List;

import com.prod.model.UserTransaction;

public interface DateWiseTransactionDao {
	List<UserTransaction> getTransactionBydate(String fromDate, String toDate);
		
	
}

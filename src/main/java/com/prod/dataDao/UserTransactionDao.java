package com.prod.dataDao;

import java.util.List;

import com.prod.model.UserTransaction;

public interface UserTransactionDao {

	void saveTransaction(UserTransaction userTransaction);

	List<UserTransaction> getSavedTransactionList(UserTransaction userTransaction);

}

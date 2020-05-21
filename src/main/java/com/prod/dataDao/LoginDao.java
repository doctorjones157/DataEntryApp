package com.prod.dataDao;

import java.util.List;

import com.prod.model.User;

public interface LoginDao {

	void validateUser(User user);

	void saveUser(User user);

	List<User> getAllUsers();

	User getUserByUserId(String userId);

	void deleteUserByUserId(String userId);

	void updateUser(User user);

}

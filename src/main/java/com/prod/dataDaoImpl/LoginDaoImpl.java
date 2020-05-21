package com.prod.dataDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.prod.dataDao.LoginDao;
import com.prod.exception.UserAlreadyExistException;
import com.prod.exception.UserNotFoundException;
import com.prod.model.User;

@Repository
public class LoginDaoImpl implements LoginDao {

	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	@Override
	public void validateUser(User user) {
			User user1 = getUser(user);
			if(user1!=null)
				System.out.println(user.getUserId()+" is authenticated.");
			else
				throw new UserNotFoundException(user.getUserId()+" - is not found");
		}

	@Override
	public void saveUser(User user) {
		if(getUser(user)!=null) {
			throw new UserAlreadyExistException();
		}else
			dynamoDBMapper.save(user);
	}
	private User getUser(User user) {
		return dynamoDBMapper.load(User.class,user.getUserId());
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = dynamoDBMapper.scan(User.class, new DynamoDBScanExpression());
		System.out.println(userList.size());
		return userList;
		
	}

	@Override
	public User getUserByUserId(String userId) {
		System.out.println(userId);
		User user = dynamoDBMapper.load(User.class, userId);
		return user;
	}

	@Override
	public void deleteUserByUserId(String userId) {
		 Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
	        expectedAttributeValueMap.put("userid", new ExpectedAttributeValue(new AttributeValue().withS(userId)));
	        DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression().withExpected(expectedAttributeValueMap);
	        User user = User.builder()
	                .userId(userId)
	                .build();
	        dynamoDBMapper.delete(user, deleteExpression);
		
	}

	@Override
	public void updateUser(User user) {
		dynamoDBMapper.save(user);
		
	}
		
	}



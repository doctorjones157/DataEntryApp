package com.prod.dataDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.prod.dataDao.DateWiseTransactionDao;
import com.prod.model.User;
import com.prod.model.UserTransaction;

@Repository
public class DateWiseTransactionDaoImpl implements DateWiseTransactionDao {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	@Override
	public List<UserTransaction> getTransactionBydate(String fromDate, String toDate) {

		List<UserTransaction> transList = new ArrayList<UserTransaction>();

		Map<String, String> attributeNames = new HashMap<String, String >();
        attributeNames.put("#transDate", "transDate");

        Map<String,AttributeValue> eav = new HashMap<String, AttributeValue>();
		  eav.put(":v1", new AttributeValue().withS(fromDate));
		  eav.put(":v2", new AttributeValue().withS(toDate));
		 
		 DynamoDBScanExpression sc = new DynamoDBScanExpression()
				 						   .withFilterExpression("#transDate between :v1 and :v2")
				 						   .withExpressionAttributeNames(attributeNames)
				 						   .withExpressionAttributeValues(eav);
		transList= dynamoDBMapper.scan(UserTransaction.class, sc);
		
		System.out.println(transList.size()+"<==LIST SIZE==> DAO");
		
		return transList;
	}

	@Override
	public String getUserNameById(String k) {
		User user = dynamoDBMapper.load(User.class, k);
		return user.getUserName();
	}

	@Override
	public List<UserTransaction> getTransactionByDateAndUserId(String fromDate, String toDate, String userId) {


		List<UserTransaction> transList = new ArrayList<UserTransaction>();

		Map<String, String> attributeNames = new HashMap<String, String >();
		attributeNames.put("#userId", "userId");
        attributeNames.put("#transDate", "transDate");

        Map<String,AttributeValue> eav = new HashMap<String, AttributeValue>();
		  eav.put(":v1", new AttributeValue().withS(fromDate));
		  eav.put(":v2", new AttributeValue().withS(toDate));
		  eav.put(":v3", new AttributeValue().withS(userId));
		 
		 DynamoDBScanExpression sc = new DynamoDBScanExpression()
				 						   .withFilterExpression("#userId =:v3 and #transDate between :v1 and :v2")
				 						   .withExpressionAttributeNames(attributeNames)
				 						   .withExpressionAttributeValues(eav);
		transList= dynamoDBMapper.scan(UserTransaction.class, sc);
		
		System.out.println(transList.size()+"<==LIST SIZE==> DAO");
		
		return transList;
	}
}

package com.prod.dataDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.prod.dataDao.DateWiseTransactionDao;
import com.prod.model.UserTransaction;

@Repository
public class DateWiseTransactionDaoImpl implements DateWiseTransactionDao {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	@Override
	public List<UserTransaction> getTransactionBydate(String fromDate, String toDate) {

		List<UserTransaction> transList = new ArrayList<UserTransaction>();

		Map<String,AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(fromDate));
		eav.put(":v2", new AttributeValue().withS(toDate));
		eav.forEach((k,v) ->{
			System.out.println(k+"<==>"+v);
		});
		
		DynamoDBQueryExpression<UserTransaction> queryExpression = new DynamoDBQueryExpression<UserTransaction>() 
			    .withKeyConditionExpression("transDate between :v1 and :v2")
			    .withExpressionAttributeValues(eav);
		
		try {
		transList = dynamoDBMapper.query(UserTransaction.class, queryExpression);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(transList.size()+"<==LIST SIZE==> DAO");
		
		return transList;
	}
}

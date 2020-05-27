package com.prod.dataDaoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.prod.dataDao.UserTransactionDao;
import com.prod.model.UserTransaction;

@Repository
public class UserTransactionDaoImpl implements UserTransactionDao {
	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	

	@Override
	public void saveTransaction(UserTransaction userTransaction) {
		System.out.println("====LIST SIZE=="+userTransaction.getTransactionList().size()+"==userTransaction.getTransDate()="+userTransaction.getTransDate());
	   // SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		//String date = dateFormatter.format();
		
		
		userTransaction.getTransactionList().forEach((trans)->{
			System.out.println("===>"+trans.getUserId()+"<====");
			trans.setTransDate(userTransaction.getTransDate());
			trans.setSingle(userTransaction.getSingle()+"-"+trans.getUserId());
			dynamoDBMapper.save(trans);
		});
		
	}

	@Override
	public List<UserTransaction> getSavedTransactionList(UserTransaction userTransaction) {
		List<UserTransaction> transList = new ArrayList<UserTransaction>();
		
		Map<String, String> attributeNames = new HashMap<String, String >();
        attributeNames.put("#single", "single");
        attributeNames.put("#transDate", "transDate");

        Map<String,AttributeValue> eav = new HashMap<String, AttributeValue>();
		  eav.put(":v2", new AttributeValue().withS(userTransaction.getTransDate()));
		  eav.put(":v1", new AttributeValue().withS(userTransaction.getSingle()));
		 
		 DynamoDBScanExpression sc = new DynamoDBScanExpression()
				 						   .withFilterExpression("begins_with(#single, :v1) and #transDate = :v2")
				 						   .withExpressionAttributeNames(attributeNames)
				 						   .withExpressionAttributeValues(eav);
		transList= dynamoDBMapper.scan(UserTransaction.class, sc);
		  
		/*
		 * Map<String,AttributeValue> eav = new HashMap<String, AttributeValue>();
		 * eav.put(":v1", new AttributeValue().withS(userTransaction.getTransDate()));
		 * eav.put(":v2", new AttributeValue().withS(userTransaction.getSingle()));
		 * eav.forEach((k,v) ->{ System.out.println(k+"<==>"+v); });
		 * 
		 * 
		 * DynamoDBQueryExpression<UserTransaction> queryExpression = new
		 * DynamoDBQueryExpression<UserTransaction>()
		 * .withKeyConditionExpression("transDate = :v1 and begins_with(single, :v2)")
		 * .withExpressionAttributeValues(eav);
		 * 
		 * try { transList = dynamoDBMapper.query(UserTransaction.class,
		 * queryExpression); }catch (Exception e) { e.printStackTrace(); }
		 * System.out.println(transList.size()+"<==LIST SIZE==> DAO");
		 */
		
		
		return transList;
	}

}

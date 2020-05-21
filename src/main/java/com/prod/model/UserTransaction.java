package com.prod.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@DynamoDBTable(tableName = "usertransactions")
public class UserTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5218084296383267229L;
	@DynamoDBHashKey(attributeName = "transDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String transDate;
	@DynamoDBRangeKey
	private String single; //userId with singles
	
	@DynamoDBAttribute(attributeName = "rate")
	private Double rate;
	@DynamoDBAttribute(attributeName = "weight")
	private Double weight;
	
	private String userId;
	@Transient
	List<UserTransaction> transactionList = new ArrayList<>();

}

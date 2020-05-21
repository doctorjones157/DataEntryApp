package com.prod.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8776487201658537447L;
	
	@NotEmpty
	@NotNull
	private String userId;
	@NotEmpty
	@NotNull
	private String password;
	
	private String userName;
	
	@DynamoDBHashKey(attributeName = "userid")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@DynamoDBAttribute(attributeName = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@DynamoDBAttribute(attributeName = "username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}

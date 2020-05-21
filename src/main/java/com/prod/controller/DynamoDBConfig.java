package com.prod.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {

	@Value("${accessKey}")
	private String AWSAccessKey;
	@Value("${secretAccessKey}")
	private String AWSSecretAccessKey;
	@Value("${region}")
	private String AWSRegion;
	@Value("${endpointurl}")
	private String AWSEndPointUrl;
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDBClient());
	}
	
	public AmazonDynamoDB amazonDynamoDBClient(){
		System.out.println(AWSEndPointUrl+ "***AWSEndPointUrl***"+AWSRegion+" ****AWSRegion****");
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(AWSEndPointUrl, AWSRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWSAccessKey, AWSSecretAccessKey)))
				.build();
	}
}

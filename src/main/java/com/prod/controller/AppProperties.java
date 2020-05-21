package com.prod.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
	
	@Value("accessKey")
	public String AWSAccessKey;
	@Value("secretAccessKey")
	public String AWSSecretAccessKey;
}

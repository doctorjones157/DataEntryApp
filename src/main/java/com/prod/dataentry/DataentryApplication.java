package com.prod.dataentry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.prod.controller","com.prod.dataDaoImpl","com.prod.ajax.controller"})
public class DataentryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataentryApplication.class, args);
	}

}

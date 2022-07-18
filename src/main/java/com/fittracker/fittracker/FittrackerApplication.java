package com.fittracker.fittracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
public class FittrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FittrackerApplication.class, args);
	}

}

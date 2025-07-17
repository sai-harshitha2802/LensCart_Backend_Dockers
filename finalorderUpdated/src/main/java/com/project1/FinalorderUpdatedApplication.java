package com.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinalorderUpdatedApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalorderUpdatedApplication.class, args);
	}

}

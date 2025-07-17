package com.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProductSeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSeviceApplication.class, args);
	}

}

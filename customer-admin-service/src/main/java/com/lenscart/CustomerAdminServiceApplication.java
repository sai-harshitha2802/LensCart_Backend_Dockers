package com.lenscart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CustomerAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAdminServiceApplication.class, args);
	}

}

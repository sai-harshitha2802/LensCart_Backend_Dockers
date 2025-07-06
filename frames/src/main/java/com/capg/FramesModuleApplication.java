package com.capg;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FramesModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FramesModuleApplication.class, args);
	}

}

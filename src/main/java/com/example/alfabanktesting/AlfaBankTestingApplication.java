package com.example.alfabanktesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.alfabanktesting.services")
@EnableDiscoveryClient
public class AlfaBankTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlfaBankTestingApplication.class, args);
	}
}





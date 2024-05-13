package com.projet9.diabete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.projet9.diabete.proxy")
public class ApiDiabeteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDiabeteApplication.class, args);
	}

}

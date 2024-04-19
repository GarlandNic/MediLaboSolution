package com.projet9.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.projet9.front")
public class MedilabFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedilabFrontApplication.class, args);
	}

}

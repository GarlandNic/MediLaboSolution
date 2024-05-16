package com.projet9.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.gateway.config.GatewayClassPathWarningAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = GatewayClassPathWarningAutoConfiguration.class)
public class MedilabGatewayApplication { 

	public static void main(String[] args) {
		SpringApplication.run(MedilabGatewayApplication.class, args);
	}

}

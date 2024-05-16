package com.projet9.front.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class CustomFeignConfiguration {
	
	private static final String CREDENTIALS_USERNAME = "userForGateway";
	private static final String CREDENTIALS_PASSWORD = "passwordForGateway";
	
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
         return new BasicAuthRequestInterceptor(CREDENTIALS_USERNAME, CREDENTIALS_PASSWORD);
    }

}

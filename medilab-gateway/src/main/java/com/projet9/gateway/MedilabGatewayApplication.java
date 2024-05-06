package com.projet9.gateway;

import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MedilabGatewayApplication {
	
	private static final String API_PATIENTS_CREDENTIALS = "userForPatientAPI:passwordForPatientAPI";
	private static final String API_PATIENTS_PATH = "http://localhost:8091"; 

	public static void main(String[] args) {
		SpringApplication.run(MedilabGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path_patients", r -> r.path("/patients/**")
						.filters(f -> f.rewritePath("/patients", "")
								.addRequestHeader("Authorization", "Basic "+Base64.getEncoder().encodeToString(API_PATIENTS_CREDENTIALS.getBytes())))
						.uri(API_PATIENTS_PATH))
//				.route("path_route", r -> r.path("/get")
//						.filters(f -> f.addRequestHeader("Hello", "World"))
//						.uri("http://httpbin.org"))
//				.route("circuit_breaker", p -> p
//						.host("*.circuitbreaker.com")
//						.filters(f -> f.circuitBreaker(config -> config
//								.setName("mycmd")
//								.setFallbackUri("forward:/fallback"))) //endpoint /fallback à définir dans controller 
//						.uri("http://httpbin.org:80"))
			.build();
	}

}

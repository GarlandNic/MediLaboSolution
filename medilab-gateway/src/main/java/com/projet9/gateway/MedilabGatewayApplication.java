package com.projet9.gateway;

import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;

@SpringBootApplication
public class MedilabGatewayApplication {
	
	private static final String API_PATIENTS_CREDENTIALS = "userForPatientsAPI:passwordForPatientsAPI";
	private static final String API_PATIENTS_PATH = "https://localhost:8091"; 

	private static final String API_NOTES_CREDENTIALS = "userForNotesAPI:passwordForNotesAPI";
	private static final String API_NOTES_PATH = "https://localhost:8092"; 

	private static final String API_DIABETE_CREDENTIALS = "userForDiabeteAPI:passwordForDiabeteAPI";
	private static final String API_DIABETE_PATH = "https://localhost:8093"; 

	public static void main(String[] args) {
		SpringApplication.run(MedilabGatewayApplication.class, args);
	}
//	
//	@Bean
//	public RouterFunction<ServerResponse> customRoute() {
//		return routes()
//				.route("path_patients", r -> r.path("/patients/**")
//						.filters(f -> f.rewritePath("/patients", "")
//								.addRequestHeader("Authorization", "Basic "+Base64.getEncoder().encodeToString(API_PATIENTS_CREDENTIALS.getBytes())))
//						.uri(API_PATIENTS_PATH))
//				.route("path_notes", r -> r.path("/notes/**")
//						.filters(f -> f.rewritePath("/notes", "")
//								.addRequestHeader("Authorization", "Basic "+Base64.getEncoder().encodeToString(API_NOTES_CREDENTIALS.getBytes())))
//						.uri(API_NOTES_PATH))
//				.route("path_diabete", r -> r.path("/diabete/**")
//						.filters(f -> f.rewritePath("/diabete", "")
//								.addRequestHeader("Authorization", "Basic "+Base64.getEncoder().encodeToString(API_DIABETE_CREDENTIALS.getBytes())))
//						.uri(API_DIABETE_PATH))
//			.build();
//	}

}

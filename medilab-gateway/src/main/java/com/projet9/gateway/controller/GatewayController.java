package com.projet9.gateway.controller;

import org.springframework.http.HttpHeaders;
//import org.springframework.cloud.gateway.server.mvc.handler.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.util.Base64;

import org.springframework.cloud.gateway.server.mvc.handler.*;
import org.springframework.cloud.gateway.server.mvc.handler.ProxyExchange.RequestBuilder;
//import org.springframework.cloud.gateway.mvc.ProxyExchange

@RestController
public class GatewayController {
	
	private static final String API_PATIENTS_CREDENTIALS = "userForPatientsAPI:passwordForPatientsAPI";
	private static final String API_PATIENTS_PATH = "http://localhost:8091"; 

	private static final String API_NOTES_CREDENTIALS = "userForNotesAPI:passwordForNotesAPI";
	private static final String API_NOTES_PATH = "http://localhost:8092"; 

	private static final String API_DIABETE_CREDENTIALS = "userForDiabeteAPI:passwordForDiabeteAPI";
	private static final String API_DIABETE_PATH = "http://localhost:8093"; 

//	@RequestMapping("/patients/**")
//	public ProxyExchange.Request proxyPatient(ProxyExchange proxy, HttpServletRequest httpReq) throws Exception {
//		String newURI = API_PATIENTS_PATH+httpReq.getRequestURI().replaceFirst("/patients", "");
//		RequestBuilder req = proxy.request(ServerRequest.create(httpReq, null));
//		HttpHeaders cred = new HttpHeaders();
//		cred.add("Authorization", "Basic "+Base64.getEncoder().encodeToString(API_PATIENTS_CREDENTIALS.getBytes()));
//		return req.uri(URI.create(newURI))
//				  .headers(cred)
//				  .build();
//	}
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
//
//}

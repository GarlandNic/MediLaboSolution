package com.projet9.gateway.controller;

//import org.springframework.cloud.gateway.server.mvc.handler.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.gateway.server.mvc.handler.*;

@RestController
public class GatewayController {
		
	@RequestMapping("/patients/**")
	public ResponseEntity<?> proxyPatient(ProxyExchange<byte[]> proxy) throws Exception {
	  String path = proxy.path("/proxy/path/");
	  return proxy.uri("/foos/" + path).get();
	}
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

package com.projet9.gateway.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

import java.util.Base64;

//import org.springframework.cloud.gateway.mvc
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class RouteConfiguration {
	
	private static final String API_PATIENTS_CREDENTIALS = "userForPatientsAPI:passwordForPatientsAPI";
	private static final String API_PATIENTS_PATH = "http://localhost:8091"; 

	private static final String API_NOTES_CREDENTIALS = "userForNotesAPI:passwordForNotesAPI";
	private static final String API_NOTES_PATH = "http://localhost:8092"; 

	private static final String API_DIABETE_CREDENTIALS = "userForDiabeteAPI:passwordForDiabeteAPI";
	private static final String API_DIABETE_PATH = "http://localhost:8093"; 

	
//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
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
////	@Bean
////    public RouterFunction<ServerResponse> gatewayRouterFunctionsPath() {
////		return GatewayRouterFunctions.route("path_route").route(null, null)
////			.route(RequestPredicates.path("/red/{segment}"), http("http://example.org"))
////			.build();
////    }
//	
//    @Bean
//    public ServerCodecConfigurer serverCodecConfigurer() {
//        return ServerCodecConfigurer.create();
//    }

}

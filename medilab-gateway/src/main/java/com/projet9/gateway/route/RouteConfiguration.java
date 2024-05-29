package com.projet9.gateway.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Base64;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;

@Configuration
public class RouteConfiguration {
	
	@Value("${my.patients.url}")
	private String API_PATIENTS_PATH; 
	private static final String API_PATIENTS_CREDENTIALS = "userForPatientsAPI:passwordForPatientsAPI";

	@Value("${my.notes.url}")
	private String API_NOTES_PATH; 
	private static final String API_NOTES_CREDENTIALS = "userForNotesAPI:passwordForNotesAPI";

	@Value("${my.diabete.url}")
	private String API_DIABETE_PATH;
	private static final String API_DIABETE_CREDENTIALS = "userForDiabeteAPI:passwordForDiabeteAPI";

    @Bean
    public RouterFunction<ServerResponse> patientsRoute() {
        return GatewayRouterFunctions.route("path_patients")
        		.route(RequestPredicates.path("/patients/**"), HandlerFunctions.http(API_PATIENTS_PATH))
        		.before(BeforeFilterFunctions.rewritePath("/patients", ""))
        		.before(BeforeFilterFunctions.setRequestHeader("Authorization", 
        				"Basic "+Base64.getEncoder().encodeToString(API_PATIENTS_CREDENTIALS.getBytes())))
        		.build();
    }
	
    @Bean
    public RouterFunction<ServerResponse> notesRoute() {
        return GatewayRouterFunctions.route("path_notes")
        		.route(RequestPredicates.path("/notes/**"), HandlerFunctions.http(API_NOTES_PATH))
        		.before(BeforeFilterFunctions.rewritePath("/notes", ""))
        		.before(BeforeFilterFunctions.setRequestHeader("Authorization", 
        				"Basic "+Base64.getEncoder().encodeToString(API_NOTES_CREDENTIALS.getBytes())))
        		.build();
    }
	
    @Bean
    public RouterFunction<ServerResponse> diabeteRoute() {
        return GatewayRouterFunctions.route("path_diabete")
        		.route(RequestPredicates.path("/diabete/**"), HandlerFunctions.http(API_DIABETE_PATH))
        		.before(BeforeFilterFunctions.rewritePath("/diabete", ""))
        		.before(BeforeFilterFunctions.setRequestHeader("Authorization", 
        				"Basic "+Base64.getEncoder().encodeToString(API_DIABETE_CREDENTIALS.getBytes())))
        		.build();
    }
	
}


package com.projet9.gateway.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
//
//@Configuration
//public class RouteConfiguration {
//	
//	@Bean
//    public RouterFunction<ServerResponse> gatewayRouterFunctionsPath() {
//		return GatewayRouterFunctions.route("path_route").route(null, null)
//			.route(RequestPredicates.path("/red/{segment}"), http("https://example.org"))
//			.build();
//    }
//
//}

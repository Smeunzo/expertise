package com.commerce.services.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	@Bean
	RouteLocator routes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r -> r.path("/users/**").uri("http://localhost:8083"))
				.route(r -> r.path("/refreshToken").uri("http://localhost:8083"))
				.route(r -> r.path("/login").uri("http://localhost:8083"))
				.route(r -> r.path("/inventory/**").uri("http://localhost:8082"))
				.route(r -> r.path("/user/**").uri("http://localhost:8081"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}

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
				.route(r -> r.path("/users/**").uri("lb://AUTH-SERVICE"))
				.route(r -> r.path("/login/**").uri("lb://AUTH-SERVICE")).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}

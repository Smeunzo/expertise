package com.commerce.services.auth;

import com.commerce.services.auth.entity.AuthCredentials;
import com.commerce.services.auth.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(AuthService authService){
		return args -> {
			AuthCredentials user1 = new AuthCredentials(null, "user1", "user1Pass");
			AuthCredentials user2 = new AuthCredentials(null, "user2", "user2Pass");
			authService.addUser(user1);
			authService.addUser(user2);
		};
	}
}

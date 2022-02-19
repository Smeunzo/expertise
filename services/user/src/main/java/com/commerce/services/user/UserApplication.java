package com.commerce.services.user;

import com.commerce.services.user.dao.AddressRepository;
import com.commerce.services.user.dao.UserRepository;

import com.commerce.services.user.entity.Address;
import com.commerce.services.user.entity.User;
import com.commerce.services.user.utils.UUIDGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	CommandLineRunner loadDatabase(UserRepository userRepository, AddressRepository addressRepository){
		return args -> {
			Address address = new Address();

			address.setStreetNumber(41);
			address.setStreetName("Rue de la marmotte ");

			address.setZipCode("13001");
			address.setCity("Marseille");
			address.setCountry("France");

			addressRepository.save(address);

			User user = new User();

			user.setFirstName("John");
			user.setLastName("Doe");
			user.setAddress(address);
			user.setEmail("email@example.fr");
			user.setBirthdate(LocalDate.of(1970, Month.JANUARY,1));

			user.setUserId(UUIDGenerator.generate());

			userRepository.save(user);
		};
	}
}

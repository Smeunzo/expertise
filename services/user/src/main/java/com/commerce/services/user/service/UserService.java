package com.commerce.services.user.service;

import com.commerce.services.user.dao.AddressRepository;
import com.commerce.services.user.dao.UserRepository;
import com.commerce.services.user.entity.User;
import com.commerce.services.user.utils.UUIDGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String customerId) {
        return userRepository.findUserByUserId(customerId);
    }

    public User createNewUser(User user) {
        user.setId(null);
        user.setUserId(UUIDGenerator.generate());

        throwIfEmailAlreadyExists(user.getEmail());
        addressRepository.save(user.getAddress());

        return userRepository.save(user);
    }

    private void throwIfEmailAlreadyExists(String email) {
        if (!userRepository.existsByEmail(email)) {
            return;
        }
        throw new RuntimeException("Customer with email : "+email+" already exists");
    }
}

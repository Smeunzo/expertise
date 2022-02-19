package com.commerce.services.user.controller;

import com.commerce.services.user.entity.User;
import com.commerce.services.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public List<User> all() {
        return userService.getUsers();
    }

    @GetMapping("/user/{userId}")
    public User one(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public User addNewCustomer(@RequestBody User user){
        return userService.createNewUser(user);
    }
}

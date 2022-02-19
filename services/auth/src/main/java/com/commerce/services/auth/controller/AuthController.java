package com.commerce.services.auth.controller;

import com.commerce.services.auth.entity.AuthCredentials;
import com.commerce.services.auth.service.AuthService;
import com.commerce.services.auth.service.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JWTService jwtService;

    @GetMapping(path = "/auth")
    public List<AuthCredentials> getUsers(){
        return authService.loadUsers();
    }

    @PostMapping(path= "/auth/addUser")
    public AuthCredentials addUser(@RequestBody AuthCredentials authCredentials){
        return authService.addUser(authCredentials);
    }

    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        jwtService.refreshToken(request,response);
    }
}

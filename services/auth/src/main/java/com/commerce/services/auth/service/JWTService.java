package com.commerce.services.auth.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.commerce.services.auth.entity.AuthCredentials;
import com.commerce.services.auth.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@AllArgsConstructor
public class JWTService {
    private final AuthService authService;

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String refreshToken = request.getHeader("Authorization");
        if (refreshToken != null && refreshToken.startsWith(JWTUtil.BEARER_TOKEN_PREFIX)) {
            DecodedJWT decodedJWT = JWTUtil.getDecodedJWT(refreshToken);
            String username = decodedJWT.getSubject();

            AuthCredentials userCredentials = authService.loadUserByUsername(username);
            String jwtAccessToken = JWTUtil.generateAccessToken(userCredentials.getUsername(),
                    request.getRequestURL().toString());

            JWTUtil.sendTokens(JWTUtil.unBearerToken(refreshToken),jwtAccessToken,response);
        }
        else{
            throw new RuntimeException("refresh token required");
        }
    }
}

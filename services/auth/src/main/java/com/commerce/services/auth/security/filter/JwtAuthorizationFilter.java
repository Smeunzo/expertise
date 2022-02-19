package com.commerce.services.auth.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.commerce.services.auth.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(JWTUtil.REFRESH_TOKEN_ROUTE) || request.getServletPath().equals(JWTUtil.REGISTER_USER_ROUTE)) {
            filterChain.doFilter(request,response);
        }
        else {
            String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);
            if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.BEARER_TOKEN_PREFIX)) {
                try {
                    createAuthToken(request, response, filterChain, authorizationToken);
                }
                catch (Exception e){
                    response.setHeader("X-Error-message",e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }
            else{
                filterChain.doFilter(request,response);
            }
        }

    }

    private void createAuthToken(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain,
                                 String authorizationToken) throws IOException, ServletException {
        DecodedJWT decodedJWT = JWTUtil.getDecodedJWT(authorizationToken);

        String username = decodedJWT.getSubject();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,null,null);

        SecurityContextHolder
                .getContext()
                .setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
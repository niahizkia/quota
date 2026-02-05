package com.quota.quota.component;

import com.quota.quota.utility.CustomAuthenticationException;
import com.quota.quota.utility.JwtManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var authorizationHeaderValue = request.getHeader("Authorization");  //Authorization itu dari posisi token di header
        String username = null;
        String token = null;

        try{
            if(authorizationHeaderValue != null){
                token = authorizationHeaderValue.replace("Bearer ", "");
                username = jwtManager.getUsername(token);
            }
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if(username != null && authentication == null){ // cek apakah username ada dan dia belum di authentication
                var userDetail = userDetailsService.loadUserByUsername(username);
                var authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetail, null, userDetail.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }catch (Exception e){
            failureHandler.onAuthenticationFailure(request, response, new CustomAuthenticationException("Token Invalid"));
            return; // untuk terminate supaya dia ngasih respon token is invalid
        }
        filterChain.doFilter(request, response);    // harus terjadi
    }
}

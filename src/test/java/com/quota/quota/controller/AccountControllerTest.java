package com.quota.quota.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quota.quota.dto.RegisterAccountDTO;
import com.quota.quota.dto.RequestTokenDTO;
import com.quota.quota.service.AccountService;
import com.quota.quota.utility.JwtManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@WebMvcTest(controllers = AccountController.class, excludeAutoConfiguration = UserDetailsServiceAutoConfiguration.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    @MockBean
    private JwtManager jwtManager;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    public void register_Success() throws Exception {
        RegisterAccountDTO request = new RegisterAccountDTO("testuser", "password", "password", "ROLE_USER");

        doNothing().when(accountService).register(any(RegisterAccountDTO.class));

        mockMvc.perform(post("/account/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rc").value("00"))
                .andExpect(jsonPath("$.rd").value("Success"));
    }

    @Test
    public void register_ValidationFailure() throws Exception {
        // Missing required fields
        RegisterAccountDTO request = new RegisterAccountDTO("", "", "", "");

        mockMvc.perform(post("/account/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest()) // Expect 400 Bad Request
                .andExpect(jsonPath("$.rc").doesNotExist()); // Check that it's NOT a success response
    }

    @Test
    public void authenticate_Success() throws Exception {
        RequestTokenDTO request = new RequestTokenDTO("testuser", "password");
        String fakeToken = "fake-jwt-token";
        String role = "ROLE_USER";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(accountService.getRole("testuser")).thenReturn(role);
        when(jwtManager.generateToken("testuser", role)).thenReturn(fakeToken);

        mockMvc.perform(post("/account/authenticate")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rc").value("00"))
                .andExpect(jsonPath("$.message.token").value(fakeToken));
    }

    @Test
    public void authenticate_BadCredentials() throws Exception {
        RequestTokenDTO request = new RequestTokenDTO("testuser", "wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        mockMvc.perform(post("/account/authenticate")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk()) // The controller catches exception and returns 200 OK with error code
                .andExpect(jsonPath("$.rc").value("401"))
                .andExpect(jsonPath("$.rd").value("Error"));
    }

    @Test
    public void authenticate_ValidationFailure() throws Exception {
        RequestTokenDTO request = new RequestTokenDTO("", "");

        mockMvc.perform(post("/account/authenticate")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest()); // Expect 400 Bad Request
    }
}

package com.quota.quota.controller;

import com.quota.quota.dto.*;
import com.quota.quota.service.AccountService;
import com.quota.quota.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final JwtManager jwtManager;
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseDTO register(@Valid @RequestBody RegisterAccountDTO request) {
        accountService.register(request);
        return new ResponseDTO("00", "Success", request);
    }

    @PostMapping("/authenticate")
    public ResponseDTO post(@Valid @RequestBody RequestTokenDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseDTO("401", "Error", "Username atau password Salah");
        }

        var role = accountService.getRole(dto.getUsername());
        var token = jwtManager.generateToken(
                dto.getUsername(),
                role);
        var responseTokenDTO = new ResponseTokenDTO(dto.getUsername(), role, token);

        return new ResponseDTO("00", "Success", responseTokenDTO);
    }
}

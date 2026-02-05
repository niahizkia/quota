package com.quota.quota.controller;

import com.quota.quota.dto.*;
import com.quota.quota.service.AccountService;
import com.quota.quota.utility.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private JwtManager jwtManager;
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody RegisterAccountDTO request){
        accountService.register(request);
        return new ResponseDTO("00", "Success", request);
    }


    @PostMapping("/authenticate")
    public ResponseDTO post(@RequestBody RequestTokenDTO dto){
        var isAuthenticated = accountService.checkUsernamePassword(dto.getUsername(), dto.getPassword());
        if(isAuthenticated){
            var role = accountService.getRole(dto.getUsername());
            var token = jwtManager.generateToken(
                    dto.getUsername(),
                    role
            );
            var responseTokenDTO = new ResponseTokenDTO(dto.getUsername(), role, token);

            return new ResponseDTO("00", "Success", responseTokenDTO); // response token ke client side

        }

        return new ResponseDTO("500", "Error", "Username atau password Salah");
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<Object> post(@RequestBody RequestTokenDTO dto){
//        var isAuthenticated = accountService.checkUsernamePassword(dto.getUsername(), dto.getPassword());
//        if(isAuthenticated){
//            var role = accountService.getRole(dto.getUsername());
//            var token = jwtManager.generateToken(
//                    dto.getUsername(),
//                    role
//            );
//            var responseDTO = new ResponseTokenDTO(dto.getUsername(), role, token);
//            return ResponseEntity.status(200).body(responseDTO);    // response token ke client side
//
//        }
//        return ResponseEntity.status(401).body("Username dan password Salah");
//    }
}

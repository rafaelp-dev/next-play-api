package com.nextplay.nextplay.controllers;

import com.nextplay.nextplay.dtos.request.UserLoginRequest;
import com.nextplay.nextplay.dtos.request.UserRegisterRequest;
import com.nextplay.nextplay.dtos.response.UserLoginResponse;
import com.nextplay.nextplay.dtos.response.UserRegisterResponse;
import com.nextplay.nextplay.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register (@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse userRegisterResponse = authService.register(userRegisterRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterResponse);
    }

    @PostMapping("login")
    public ResponseEntity<UserLoginResponse> login (@Valid @RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = authService.login(userLoginRequest);

        return ResponseEntity.ok().body(userLoginResponse);
    }
}

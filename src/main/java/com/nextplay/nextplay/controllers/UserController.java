package com.nextplay.nextplay.controllers;

import com.nextplay.nextplay.dtos.request.UserProfileRequest;
import com.nextplay.nextplay.dtos.response.UserProfileResponse;
import com.nextplay.nextplay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-profile")
    public ResponseEntity<UserProfileResponse> createUserProfile (Authentication authentication, @Valid @RequestBody UserProfileRequest userProfileRequest) {
        UserProfileResponse userProfileResponse = userService.createUserProfile(authentication, userProfileRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileResponse);
    }
}

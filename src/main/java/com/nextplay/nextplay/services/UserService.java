package com.nextplay.nextplay.services;

import com.nextplay.nextplay.dtos.request.UserProfileRequest;
import com.nextplay.nextplay.dtos.response.UserProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserProfileService userProfileService;

    public UserService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    public UserProfileResponse createUserProfile(Authentication authentication, UserProfileRequest userProfileRequest) {
        return userProfileService.createUserProfile(authentication, userProfileRequest);
    }
}

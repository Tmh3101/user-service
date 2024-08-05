package com.Tmh3101.user_manager.controller;

import com.Tmh3101.user_manager.dto.request.ApiResponse;
import com.Tmh3101.user_manager.dto.request.AuthenticationRequest;
import com.Tmh3101.user_manager.dto.response.AuthenticationResponse;
import com.Tmh3101.user_manager.service.Impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        boolean res = authenticationServiceImpl.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(res)
                        .build())
                .build();
    }

}

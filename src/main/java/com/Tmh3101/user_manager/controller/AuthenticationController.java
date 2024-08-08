package com.Tmh3101.user_manager.controller;

import com.Tmh3101.user_manager.dto.request.ApiResponse;
import com.Tmh3101.user_manager.dto.request.AuthenticationRequest;
import com.Tmh3101.user_manager.dto.request.IntrospectRequest;
import com.Tmh3101.user_manager.dto.response.AuthenticationResponse;
import com.Tmh3101.user_manager.dto.response.IntrospectResponse;
import com.Tmh3101.user_manager.service.Impl.AuthenticationServiceImpl;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse res = authenticationServiceImpl.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(res)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {

        IntrospectResponse res = authenticationServiceImpl.introspect(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(res)
                .build();
    }
}

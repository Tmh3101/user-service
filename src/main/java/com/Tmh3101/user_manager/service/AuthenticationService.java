package com.Tmh3101.user_manager.service;

import com.Tmh3101.user_manager.dto.request.AuthenticationRequest;
import com.Tmh3101.user_manager.dto.request.IntrospectRequest;
import com.Tmh3101.user_manager.dto.response.AuthenticationResponse;
import com.Tmh3101.user_manager.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}

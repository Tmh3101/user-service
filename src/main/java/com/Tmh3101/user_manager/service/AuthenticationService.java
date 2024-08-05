package com.Tmh3101.user_manager.service;

import com.Tmh3101.user_manager.dto.request.AuthenticationRequest;

public interface AuthenticationService {
    public boolean authenticate(AuthenticationRequest authenticationRequest);
}

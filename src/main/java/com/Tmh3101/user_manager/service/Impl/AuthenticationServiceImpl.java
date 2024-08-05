package com.Tmh3101.user_manager.service.Impl;

import com.Tmh3101.user_manager.dto.request.AuthenticationRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.entity.User;
import com.Tmh3101.user_manager.exception.AppException;
import com.Tmh3101.user_manager.exception.ErrorCode;
import com.Tmh3101.user_manager.repo.UserRepo;
import com.Tmh3101.user_manager.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    public UserRepo userRepo;

    @Override
    public boolean authenticate(AuthenticationRequest authenticationRequest) {
        User user = userRepo.findUserByEmail(authenticationRequest.getEmail()).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND_ANY_USERS)
        );
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
    }
}

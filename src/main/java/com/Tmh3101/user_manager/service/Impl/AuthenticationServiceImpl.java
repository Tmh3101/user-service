package com.Tmh3101.user_manager.service.Impl;

import com.Tmh3101.user_manager.dto.request.AuthenticationRequest;
import com.Tmh3101.user_manager.dto.request.IntrospectRequest;
import com.Tmh3101.user_manager.dto.response.AuthenticationResponse;
import com.Tmh3101.user_manager.dto.response.IntrospectResponse;
import com.Tmh3101.user_manager.entity.User;
import com.Tmh3101.user_manager.exception.AppException;
import com.Tmh3101.user_manager.exception.ErrorCode;
import com.Tmh3101.user_manager.repo.UserRepo;
import com.Tmh3101.user_manager.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    UserRepo userRepo;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        User user = userRepo.findUserByEmail(authenticationRequest.getEmail())
                            .orElseThrow(
                                    () -> new AppException(ErrorCode.NOT_FOUND_ANY_USERS)
                            );
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(User user){

        //Header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        //Claim in Payload
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("Tmh3101.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();

        //Payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            //Signature
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot generate token", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest)
            throws JOSEException, ParseException {

        String token = introspectRequest.getToken();
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean verified = signedJWT.verify(jwsVerifier);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean expired = expiryTime.after(new Date());

        return IntrospectResponse.builder()
                .valid(verified && expired)
                .build();
    }

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                role.getPermissions().forEach(permission ->
                    stringJoiner.add(permission.getName())
                );
            }
        );
        return stringJoiner.toString();
    }
}

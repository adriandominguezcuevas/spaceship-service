package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.usecase.AuthenticateUserUseCase;
import com.w2m.spaceship.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserCaseImpl implements AuthenticateUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticateUserCaseImpl.class);
    private static final String LOG_HEADER = "[APPLICATION][AUTHENTICATE][USE-CASE]";

    public AuthenticateUserCaseImpl(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String authenticate(String username, String password) {
        try {
            logger.info("{} Authenticating user: {}", LOG_HEADER, username);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            return jwtTokenProvider.generateToken(authentication);
        } catch (BadCredentialsException ex) {
            logger.warn("{} Invalid credentials for user: {}", LOG_HEADER, username);
            throw ex;
        }
    }

}

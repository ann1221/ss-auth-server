package com.test.authserver.provider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class LoggingDaoAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Authentication auth = super.authenticate(authentication);
            // todo: возможно необходимы доп дейсвтия
            // set attempts = 0;
            return auth;
        } catch (AuthenticationException e) {
            // todo: занести в логи
            // set attempts++;
            throw e;
        }
    }
}

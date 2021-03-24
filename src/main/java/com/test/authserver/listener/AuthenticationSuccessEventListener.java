package com.test.authserver.listener;

import com.test.authserver.service.LoginAttemptService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final LoginAttemptService loginAttemptService = LoginAttemptService.getInstance();

    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails)
                e.getAuthentication().getDetails();

        loginAttemptService.loginSucceed(auth.getRemoteAddress());

        log.info("SUCCESS " + auth.getRemoteAddress() + " " + auth.getSessionId());
        log.info("ALOHA" + e.getAuthentication().getName());
    }
}

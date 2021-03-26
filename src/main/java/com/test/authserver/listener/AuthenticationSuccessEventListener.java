package com.test.authserver.listener;

import com.test.authserver.model.entity.AuthUser;
import com.test.authserver.model.entity.log.AuthServerLog;
import com.test.authserver.model.entity.log.Level;
import com.test.authserver.model.entity.log.LogName;
import com.test.authserver.service.AuthServerLogService;
import com.test.authserver.service.LoginAttemptService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private AuthServerLogService logService;

    private final LoginAttemptService loginAttemptService = LoginAttemptService.getInstance();

    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();

        loginAttemptService.loginSucceed(auth.getRemoteAddress());
        loginAttemptService.loginFailed(auth.getRemoteAddress());
        // todo: добавить auth-user
        logService.add(AuthServerLog.withLogName(LogName.USER_AUTHENTICATION_SUCCEED)
                .level(Level.INFO)
                .username(e.getAuthentication().getName())
                .currDate()
                .build()
        );
    }
}

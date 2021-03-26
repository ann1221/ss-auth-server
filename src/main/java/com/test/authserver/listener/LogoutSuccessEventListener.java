package com.test.authserver.listener;

import com.test.authserver.model.entity.AuthUser;
import com.test.authserver.model.entity.log.AuthServerLog;
import com.test.authserver.model.entity.log.Level;
import com.test.authserver.model.entity.log.LogName;
import com.test.authserver.service.AuthServerLogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class LogoutSuccessEventListener implements ApplicationListener<LogoutSuccessEvent> {
    @Autowired
    private AuthServerLogService logService;

    @Override
    public void onApplicationEvent(LogoutSuccessEvent e) {
        WebAuthenticationDetails details = (WebAuthenticationDetails) e.getAuthentication().getDetails();

        // todo: добавить auth-user
        logService.add(AuthServerLog.withLogName(LogName.USER_LOGOUT)
                .level(Level.INFO)
                .username(e.getAuthentication().getName())
                .currDate()
                .build()
        );
    }
}

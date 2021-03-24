package com.test.authserver.listener;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class LogoutSuccessEventListener implements ApplicationListener<LogoutSuccessEvent> {
    @Override
    public void onApplicationEvent(LogoutSuccessEvent e) {
        WebAuthenticationDetails details = (WebAuthenticationDetails)
                e.getAuthentication().getDetails();
        log.info("SUCCESS LOGOUT" + details.getRemoteAddress() + " " + details.getSessionId());
        log.info("ALOHA" + e.getAuthentication().getName());
    }
}

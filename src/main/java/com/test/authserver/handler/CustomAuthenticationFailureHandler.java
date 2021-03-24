package com.test.authserver.handler;

import com.test.authserver.model.SecurityUser;
import com.test.authserver.service.LoginAttemptService;
import com.test.authserver.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
        if (exception.getMessage().equalsIgnoreCase(SecurityUserService.IP_BLOCKED)) {
            request.getSession()
                    .setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, "ip address blocked");
        }
    }
}

package com.test.authserver.provider;

import com.test.authserver.service.LoginAttemptService;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class CustomDaoAuthProvider extends DaoAuthenticationProvider {
    public static String IP_BLOCKED = "blocked";
    private final static LoginAttemptService ATTEMPT_CACHE = LoginAttemptService.getInstance();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (ATTEMPT_CACHE.isBlocked(getUserRemoteAddr())) {
            throw new LockedException(IP_BLOCKED);
        }
        return super.authenticate(authentication);
    }

    private String getUserRemoteAddr() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}

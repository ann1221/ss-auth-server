package com.test.authserver.filter;

import com.test.authserver.model.entity.log.AuthServerLog;
import com.test.authserver.model.entity.log.Level;
import com.test.authserver.model.entity.log.LogName;
import com.test.authserver.service.AuthServerLogService;
import com.test.authserver.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.util.Pair;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@PropertySource("classpath*:settings.properties")
public class IpCheckerFilter extends OncePerRequestFilter {
    private final String errorMessage = "IP address blocked until ";
    private final Short maxLoginAttemptsCount = 5;
    private final LoginAttemptService ATTEMPT_CACHE = LoginAttemptService.getInstance();

    private AuthServerLogService logService;

    public IpCheckerFilter(AuthServerLogService logService) {
        this.logService = logService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String ipAddr = getUserRemoteAddr(request);
        if (isBlocked(ipAddr)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, errorMessage + getBlockTimeIntervalEnd(ipAddr));
            logService.add(
                    AuthServerLog.withLogName(LogName.IP_BLOCKED)
                            .level(Level.WARN)
                            .description(errorMessage + getBlockTimeIntervalEnd(ipAddr))
                            .ipAddr(ipAddr)
                            .currDate()
                            .build()
            );
            return;
        }
        chain.doFilter(request, response);
    }

    private Date getBlockTimeIntervalEnd(String ipAddr) {
        long date = ATTEMPT_CACHE.getLastAttemptTime(ipAddr);
        Pair<Short, TimeUnit> expParams = ATTEMPT_CACHE.getExpirationParams();
        return new Date(date + expParams.getSecond().toMillis(expParams.getFirst()));
    }

    private Boolean isBlocked(String IpAddr) {
        return ATTEMPT_CACHE.getFailureAttemptsCount(IpAddr) >= maxLoginAttemptsCount;
    }

    private String getUserRemoteAddr(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }


}

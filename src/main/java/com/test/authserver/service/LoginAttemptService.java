package com.test.authserver.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sun.istack.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.TimeUnit;

public class LoginAttemptService {
    private final static LoginAttemptService INSTANCE = new LoginAttemptService();
    private final static Short MAX_ATTEMPT_COUNT = 5;
    private final LoadingCache<String, Short> failureAttempts;

    private LoginAttemptService() {
        CacheLoader<String, Short> loader = new CacheLoader<>() {
            @Override
            public Short load(@NotNull String s) {
                return 0;
            }
        };
        failureAttempts = CacheBuilder
                .newBuilder()
                .expireAfterWrite(3, TimeUnit.MINUTES)
                .build(loader);
    }

    public static LoginAttemptService getInstance() {
        return INSTANCE;
    }

    public void loginFailed(String ipAddr) {
        Short attempts = failureAttempts.getUnchecked(ipAddr);
        failureAttempts.put(ipAddr, ++attempts);
    }

    public void loginSucceed(String ipAddr) {
        failureAttempts.invalidate(ipAddr);
    }

    public Boolean isBlocked(String ipAddr) {
        return failureAttempts.getUnchecked(ipAddr) >= MAX_ATTEMPT_COUNT;
    }
}

package com.test.authserver.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sun.istack.NotNull;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginAttemptService {
    private final static LoginAttemptService INSTANCE = new LoginAttemptService();
    private LoadingCache<String, Short> failureAttempts;
    private final Map<String, Date> lastAttemptTime = new HashMap<>();
    private Short expireDuration;
    private TimeUnit expireTimeUnit;
    private final CacheLoader<String, Short> loader = new CacheLoader<>() {
        @Override
        public Short load(@NotNull String s) {
            return 0;
        }
    };

    private LoginAttemptService() {
        short duration = 3;
        reinitializeCache(duration, TimeUnit.MINUTES);
    }

    public static LoginAttemptService getInstance() {
        return INSTANCE;
    }

    public void reinitializeCache(Short newExpireDuration, TimeUnit newExpireTimeUnit) {
        expireDuration = newExpireDuration == null ? 3 : newExpireDuration;
        expireTimeUnit = newExpireTimeUnit == null ? TimeUnit.MINUTES : newExpireTimeUnit;
        failureAttempts = CacheBuilder
                .newBuilder()
                .expireAfterWrite(expireDuration, expireTimeUnit)
                .build(loader);
    }

    public void loginFailed(String ipAddr) {
        Short attempts = failureAttempts.getUnchecked(ipAddr);
        failureAttempts.put(ipAddr, ++attempts);
        setLastAttemptTime(ipAddr);
    }

    public void loginSucceed(String ipAddr) {
        failureAttempts.invalidate(ipAddr);
        setLastAttemptTime(ipAddr);
    }

    private void setLastAttemptTime(String ipAddr) {
        lastAttemptTime.put(ipAddr, new Date());
    }

    public Short getFailureAttemptsCount(String ipAddr) {
        return failureAttempts.getUnchecked(ipAddr);
    }

    public long getLastAttemptTime(String ipAddr) {
        return lastAttemptTime.getOrDefault(ipAddr, null).getTime();
    }

    public Pair<Short, TimeUnit> getExpirationParams() {
        return Pair.of(expireDuration, expireTimeUnit);
    }
}

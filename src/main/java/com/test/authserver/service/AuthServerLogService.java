package com.test.authserver.service;

import com.test.authserver.model.entity.log.AuthServerLog;
import com.test.authserver.model.entity.log.Level;
import com.test.authserver.repo.AuthServerLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthServerLogService {
    private final AuthServerLogRepo repo;

    @Autowired
    public AuthServerLogService(AuthServerLogRepo authServerLogRepo) {
        repo = authServerLogRepo;
    }

    public List<AuthServerLog> getAll() {
        return repo.findAll();
    }

    public List<AuthServerLog> getByUserId(String username) {
        return repo.getByUsername(username);
    }

    public List<AuthServerLog> getByLogLevel(Level level) {
        return repo.getByLevel(level);
    }

    public List<AuthServerLog> getByDate(Date date) {
        return repo.getByDate(date);
    }

    public AuthServerLog add(AuthServerLog authServerLog) {
        return repo.save(authServerLog);
    }
}

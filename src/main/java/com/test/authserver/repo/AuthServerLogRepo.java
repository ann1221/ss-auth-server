package com.test.authserver.repo;

import com.sun.istack.NotNull;
import com.test.authserver.model.entity.log.AuthServerLog;
import com.test.authserver.model.entity.log.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AuthServerLogRepo extends JpaRepository<AuthServerLog, UUID> {
    List<AuthServerLog> findAll();

    List<AuthServerLog> getByUsername(String username);
    List<AuthServerLog> getByDate(Date date);
    List<AuthServerLog> getByLevel(Level level);
    List<AuthServerLog> getByName(String name);

    AuthServerLog save(AuthServerLog serverLog);
}

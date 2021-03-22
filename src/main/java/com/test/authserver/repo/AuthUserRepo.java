package com.test.authserver.repo;//package com.qsystem.authserver.repo;

import com.test.authserver.model.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthUserRepo extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findFirstByLogin(String login);

    List<AuthUser> findAll();
}

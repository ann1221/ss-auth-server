package com.test.authserver.repo;//package com.qsystem.authserver.repo;

import com.test.authserver.model.entity.AuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthClientRepo extends JpaRepository<AuthClient, Long> {

    List<AuthClient> findAll();

    @Override
    Optional<AuthClient> findById(Long aLong);

    Optional<AuthClient> findFirstByName(String clientName);

}

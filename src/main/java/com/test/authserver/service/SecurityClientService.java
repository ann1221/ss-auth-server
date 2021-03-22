package com.test.authserver.service;//package com.qsystem.authserver.service;

import com.test.authserver.model.SecurityClient;
import com.test.authserver.repo.AuthClientRepo;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public class SecurityClientService implements ClientDetailsService {
    private AuthClientRepo authClientRepo;

    public ClientDetailsService setAuthClientRepo(AuthClientRepo authClientRepo) {
        this.authClientRepo = authClientRepo;
        return this;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return authClientRepo.findFirstByName(clientId)
                .map(SecurityClient::new)
                .orElseThrow(() -> new ClientRegistrationException("i can't find your client, baby"));
    }
}

package com.test.authserver.model;

import com.test.authserver.model.entity.AuthClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityClient implements ClientDetails {
    private final static Integer TOKEN_VALIDITY_SECONDS = 5000;
    private final static Integer REFRESH_TOKEN_VALIDITY_SECONDS = 6000;

    private final AuthClient authClient;

    public SecurityClient(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    public String getClientId() {
        return authClient.getName();
    }

    @Override
    public boolean isSecretRequired() {
        return authClient.getSecret() != null && !authClient.getSecret().trim().isEmpty();
    }

    @Override
    public String getClientSecret() {
        return authClient.getSecret();
    }

    @Override
    public Set<String> getResourceIds() {
        return authClient.getResources().stream()
                .map(resource -> resource.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isScoped() {
        return authClient.getScopes() != null && !authClient.getScopes().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return authClient.getScopes().stream()
                .map(scope -> scope.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authClient.getGrantTypes().stream()
                .map(grant -> grant.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return authClient.getRedirectUris().stream()
                .map(redirect -> redirect.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authClient.getAuthorities().stream()
                .map(authority -> (GrantedAuthority)authority)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return TOKEN_VALIDITY_SECONDS;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return REFRESH_TOKEN_VALIDITY_SECONDS;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return authClient.getIsAutoApproved();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}

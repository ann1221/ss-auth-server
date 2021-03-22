package com.test.authserver.model;

import com.test.authserver.model.entity.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser implements UserDetails {
    private final AuthUser authUser;

    public SecurityUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public String getUsername() {
        return authUser.getLogin();
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authUser.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return authUser.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return authUser.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return authUser.getIsEnabled();
    }
}

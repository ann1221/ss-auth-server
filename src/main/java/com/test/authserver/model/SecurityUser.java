package com.test.authserver.model;

import com.test.authserver.model.entity.AuthUser;
import com.test.authserver.model.entity.Authority;
import com.test.authserver.model.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class SecurityUser implements UserDetails {
    @Getter
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

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authUser.getRoles();
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> userRolesAuthorities = authUser.getRoles().stream()
                .map(Role::getAuthorities)
                .reduce((authorities, authorities2) -> {
                    authorities.addAll(authorities2);
                    return authorities;
                }).orElse(new HashSet<>());
        Set<Authority> userAuthorities = authUser.getAuthorities();
        userAuthorities.addAll(userRolesAuthorities);
        return userAuthorities;
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

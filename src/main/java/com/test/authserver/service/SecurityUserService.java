package com.test.authserver.service;

import com.test.authserver.model.SecurityUser;
import com.test.authserver.repo.AuthUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

public class SecurityUserService implements UserDetailsManager {
    private AuthUserRepo authUserRepo;

    public UserDetailsManager setAuthUserRepo(AuthUserRepo authUserRepo) {
        this.authUserRepo = authUserRepo;
        return this;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return authUserRepo.findFirstByLogin(login)
                .map(authUser -> new SecurityUser(authUser))
                .orElseThrow(() -> new UsernameNotFoundException("i can't find your user, baby"));
    }

    @Override
    public boolean userExists(String login) {
        return authUserRepo.findFirstByLogin(login).isPresent();
    }

    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }
}

package com.test.authserver.service;

import com.test.authserver.model.SecurityUser;
import com.test.authserver.repo.AuthUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Transactional
public class SecurityUserService implements UserDetailsManager {
    public static String IP_BLOCKED = "blocked";
    private AuthUserRepo authUserRepo;
    private final static LoginAttemptService ATTEMPT_CACHE = LoginAttemptService.getInstance();

    public SecurityUserService setAuthUserRepo(AuthUserRepo authUserRepo) {
        this.authUserRepo = authUserRepo;
        return this;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        if (ATTEMPT_CACHE.isBlocked(getUserRemoteAddr())) {
//            throw new RuntimeException(IP_BLOCKED);
//        }
        return authUserRepo.findFirstByLogin(login)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("i can't find your user, baby"));
    }

//    private String getUserRemoteAddr() {
//        HttpServletRequest request =
//                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                        .getRequest();
//        String xfHeader = request.getHeader("X-Forwarded-For");
//        if (xfHeader == null){
//            return request.getRemoteAddr();
//        }
//        return xfHeader.split(",")[0];
//    }

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

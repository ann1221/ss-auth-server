package com.test.authserver.HelloController;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("authentic")
    public Object getAuthentication(Authentication authentication) {
        return authentication;
    }

    @GetMapping("principal")
    public Object getPrincipal(Principal principal) {
        return principal;
    }

    @GetMapping("session")
    public Object getSession(SessionStatus sessionStatus) {
        return sessionStatus;
    }


    @GetMapping("public/hello")
    public String getPublicHello(SessionStatus sessionStatus) {
        return "Very Public Hello from Auth Server!";
    }
}

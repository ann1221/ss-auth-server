package com.test.authserver.config;

import com.test.authserver.repo.AuthUserRepo;
import com.test.authserver.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

// todo: нет обработки ошибок для oauht2, то есть при ошибки нас перенаправляет на /oauth/error, а она не реалзиована
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthUserRepo authUserRepo;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .mvcMatchers("/oauth/**", "/error**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login")
                .permitAll()
        ;

    }

    @Override
    public void configure(WebSecurity web) {
        web
           .ignoring()
                .mvcMatchers("/css/**", "/js/**", "/img/**", "/lang/**")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService())
        ;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new SecurityUserService().setAuthUserRepo(authUserRepo);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

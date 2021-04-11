package com.test.authserver.config;

import com.test.authserver.filter.IpCheckerFilter;
import com.test.authserver.repo.AuthUserRepo;
import com.test.authserver.service.AuthServerLogService;
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
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthUserRepo authUserRepo;
    @Autowired
    AuthServerLogService authServerLogService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .mvcMatchers("/oauth/**", "/error**", "/public/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login")
                .permitAll()
        ;
        setFilters(http);
    }

    protected void setFilters(HttpSecurity http) {
        http.addFilterBefore(ipCheckerFilter(), CsrfFilter.class);
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
            .passwordEncoder(passwordEncoder())
        ;
    }

    @Bean
    public OncePerRequestFilter ipCheckerFilter() {
        return new IpCheckerFilter(authServerLogService);
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

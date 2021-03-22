package com.test.authserver.config.inits;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


//EmbeddedServletContainerCustomizer

@Configuration
@ComponentScan(basePackages = {"com.test.authserver"})
@EnableWebMvc
public class RootAppConfig {
}

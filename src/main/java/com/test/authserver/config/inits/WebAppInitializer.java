package com.test.authserver.config.inits;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
        rootAppContext.register(RootAppConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(rootAppContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("DispatcherServlet", servlet);
        registration.setLoadOnStartup(1);
        // dispatcher.setAsyncSupported(true);
        registration.addMapping("/*");

        servletContext.addListener(new ContextLoaderListener(rootAppContext)); // вроде нужно для работы spring-security
        servletContext.addListener(new RequestContextListener()); // необходимо для получения в любом месте HttpServletRequest
    }
}

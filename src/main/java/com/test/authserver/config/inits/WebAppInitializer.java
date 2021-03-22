package com.test.authserver.config.inits;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


/**
 * com/nimbusds/oauth2/sdk/http/HTTPResponse необходимо подключать данную зависимость, это менеджер, который
 * работет в runtime, в данном случае необходимо подключить oauth2-oidc-sdk (oidc = open id connect), это менеджер
 * умеет делать запросы на проверку opaque-токена
 */

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
        rootAppContext.register(RootAppConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(rootAppContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("DispatcherServlet", servlet);
        registration.setLoadOnStartup(1);
        // dispatcher.setAsyncSupported(true);
        registration.addMapping("/*"); // при изменении на что-то другое начинает говорить, что не хватает фильтра, чтобы осуществить аутентификацию

        servletContext.addListener(new ContextLoaderListener(rootAppContext)); // обычно не добавляем, но тут почему-то потребовали, похоже это из-за security
    }
}

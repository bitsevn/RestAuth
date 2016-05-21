package com.cubestacklabs.restauth;

import com.cubestacklabs.restauth.auth.AuthenticationHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.cubestacklabs.arrow")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthenticationHandlerInterceptor authenticationHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationHandlerInterceptor);
    }
}

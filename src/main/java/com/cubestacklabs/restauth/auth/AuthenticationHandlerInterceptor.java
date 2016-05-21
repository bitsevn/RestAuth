package com.cubestacklabs.restauth.auth;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthenticationHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(AuthenticationHandlerInterceptor.class);

    private static final String USER_ID = "APP-USER-ID";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";// this could be an encoded string
    private static final String START_TIME = "START_TIME";

    private ConcurrentHashMap<String, User> cache = new ConcurrentHashMap<String, User>(100);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if( ! (handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Annotation annotation = handlerMethod.getMethod().getAnnotation(AuthNotRequired.class);

        if(annotation != null) {
            return true;
        }

        String token = request.getHeader(AUTH_TOKEN);

        String userId = parse(token);

        User user = null;
        if(userId == null) return error(response);
        if((user = cache.get(userId)) == null) return error(response);

        user = userService.find(userId);

        cache.putIfAbsent(userId, user);

        return true;
    }

    private String parse(String token) {
        if(token == null) return null;
        //TODO
        return token;
    }

    private boolean error(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write("Unauthorized Access");
        response.sendError(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}

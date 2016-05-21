package com.cubestacklabs.restauth.auth;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public User find(String userId) {
        return new User(userId);
    }
}

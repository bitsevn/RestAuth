package com.cubestacklabs.restauth.controller;

import com.cubestacklabs.restauth.auth.AuthNotRequired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "Howdy!";
    }

    @RequestMapping("users")
    public String users() {
        return "users";
    }

    @RequestMapping("repos")
    public String repos() {
        return "repos";
    }

    @AuthNotRequired
    @RequestMapping("issues")
    public String issues() {
        return "issues";
    }

    @RequestMapping("gists")
    public String gists() {
        return "gists";
    }
}

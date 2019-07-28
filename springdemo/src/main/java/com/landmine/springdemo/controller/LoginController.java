package com.landmine.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: lanrish
 * date  : 2019-07-15
 * desc  :
 * <pre></pre>
 */
@RestController
public class LoginController {
    @Autowired
    private Environment environment;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("username:"+username+",password:"+password);
        return null;
    }

    @RequestMapping("/blog")
    public String login(){
        return environment.getProperty("url");
    }
}

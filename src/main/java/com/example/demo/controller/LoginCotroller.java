package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class LoginCotroller {
    @GetMapping("login")
    public String index(){
        return "login";
    }
    @PostMapping("login")
    public void login(String username,String password){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
        }catch (UnknownAccountException e){
            e.printStackTrace();
        }
    }
}

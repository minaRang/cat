package com.backendStudy.cat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/users")
@Controller
public class UserController {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @GetMapping("/login")
    public String login(){
        log.info("============================");
        log.info("login 실행");
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        log.info("============================");
        log.info("signup 실행");
        return "signup";
    }

    @GetMapping("/myPage")
    public String myPage(){
        log.info("============================");
        log.info("myPage 실행");
//        UserDetails user = (UserDetails) authentication.getPrincipal();
//        log.info("유저는 "+authentication.getName()+" 권한은"+authentication.getAuthorities());
        return "myPage";
    }

    @GetMapping("/denied")
    public String denied(){
        log.info("============================");
        log.info("denied 실행");
//        UserDetails user = (UserDetails) authentication.getPrincipal();
//        if(user!=null){
//            log.info("유저는 "+user.getUsername()+" 권한은"+user.getAuthorities());}
        return "denied";
    }

}

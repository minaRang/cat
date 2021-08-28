package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOUser;
import com.backendStudy.cat.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/users")
@Controller
public class UserController {
    Authentication auth;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public String login(Model model){
        log.info("============================");
        log.info("login 실행");
        userService.mailSend();
        return "login";
    }

    @GetMapping("/logins")
    public String loginSuccesses(@RequestParam(value = "result",required = false) int result, Model model){
        log.info("login 실행2");

        if(result==1){
            log.info("login 성공");
            model.addAttribute("msg","로그인에 성공하였습니다.");
            model.addAttribute("url","/users/myPage");
        }
        else{
            log.info("login 실패");
            model.addAttribute("msg","로그인에 실패하였습니다.");
            model.addAttribute("url","/users/login");
        }
        return "redirect";
    }

    @GetMapping("/signup")
    public String signup(){
        log.info("============================");
        log.info("signup 실행");
        return "signup";
    }

    @GetMapping("/myPage")
    public String myPage(Model model){
        log.info("============================");
        log.info("myPage 실행");
        auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("사용자는: "+auth.getName());
        model.addAttribute("user",userService.getUser(auth.getName()));
        return "myPage";
    }

    @RequestMapping(value="/myPage", method=RequestMethod.POST)
    @ResponseBody
    public void modifyMyPage(@RequestBody DTOUser user){
        log.info("============================");
        log.info("myPage 수정");
        log.info(user.toString());
        userService.updateUser("detail",user);
    }

    @GetMapping("/denied")
    public String denied(){
        log.info("============================");
        log.info("denied 실행");
        return "denied";
    }

}

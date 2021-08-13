package com.backendStudy.cat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {
    @GetMapping("/test")
    public String adminTest(){
        log.info("============================");
        log.info("admin test 실행");
        return "admin";
    }
}

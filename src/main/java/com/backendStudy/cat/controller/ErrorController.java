package com.backendStudy.cat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ErrorController {
    @GetMapping("error")
    public String error(){
        log.info("============================");
        log.info("error 실행");
        return "error";
    }
}

package com.sample.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/api")
    public @ResponseBody String home() {
    	log.debug("Enter in API home");
        return "Hello World";
    }
}

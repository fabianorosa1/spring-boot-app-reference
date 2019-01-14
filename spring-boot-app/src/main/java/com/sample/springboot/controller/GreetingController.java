package com.sample.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.springboot.service.GreetingService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GreetingController {

	@Autowired
    private GreetingService service;

    @RequestMapping("/api/greeting")
    public @ResponseBody String greeting() {
    	log.debug("Enter in API greeting");
        return service.greet();
    }

}

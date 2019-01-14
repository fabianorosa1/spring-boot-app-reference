package com.sample.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.sample.springboot.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greet() {
		return "Hello World";
	}
}

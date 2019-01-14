package com.sample.springboot;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfig.class)
public abstract class SpringBootBaseIT {

    private final String SERVER_URL = "http://localhost";

    private RestTemplate restTemplate;

    @Value("${server.port}")
    private int port;   

    public SpringBootBaseIT() {
    	System.out.println("### serverEndpoint: " + serverEndpoint());
        restTemplate = new RestTemplate();
    }

    private String serverEndpoint() {
        return SERVER_URL + ":" + port ;
    }

    int put(final String something) {
        return restTemplate.postForEntity(serverEndpoint(), something, Void.class).getStatusCodeValue();
    }
}
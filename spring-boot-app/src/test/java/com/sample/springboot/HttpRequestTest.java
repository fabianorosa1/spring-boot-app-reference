package com.sample.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;   

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {    	
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api",
                String.class)).contains("Hello World");
    }
    
    @TestConfiguration
    static class TestRestTemplateAuthenticationConfiguration {

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
        	// set the basic auth to http request
            return new RestTemplateBuilder().basicAuthorization("user", "user");
        }
    }
}

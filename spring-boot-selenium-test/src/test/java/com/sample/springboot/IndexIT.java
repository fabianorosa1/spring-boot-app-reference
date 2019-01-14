/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class, Application.class})
public class IndexIT {

    @Autowired
    private WebDriver webDriver;

    @Value("${server.port}")
    private int serverPort;

    @Test
    public void visitIndexPage() throws Exception {    	    	
    	String URL = String.format("http://user:user@localhost:%d/index.html", serverPort);
    	System.out.println("IndexIT Url: " + URL);
    	
        webDriver.get(URL);
        
        WebElement working = webDriver.findElement(By.id("working"));
        Assert.assertThat(working.getText(), is(equalTo("It works!")));
        
        WebElement helloWorld = webDriver.findElement(By.id("service"));
        Assert.assertThat(helloWorld.getText(), is(equalTo("Hello World")));
    }   
}

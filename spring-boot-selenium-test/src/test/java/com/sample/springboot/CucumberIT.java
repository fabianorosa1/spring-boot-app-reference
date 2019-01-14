package com.sample.springboot;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json" },
	features = "classpath:features",
	monochrome = false)
public class CucumberIT {
}

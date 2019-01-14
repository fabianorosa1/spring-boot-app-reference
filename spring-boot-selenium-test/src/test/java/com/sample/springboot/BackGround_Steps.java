package com.sample.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = ITConfig.class)
public class BackGround_Steps extends SpringBootBaseIT {

    @Value("${server.port}")
    private int port;   
    
	@Given("^I navigate to the login page$")
	public void i_navigate_to_the_login_page() throws Throwable {
		System.out.println("I am at the LogIn Page PORT: " + port);
	}

	@When("^I submit username and password$")
	public void i_submit_username_and_password() throws Throwable {
		System.out.println("I Submit my Username and Password");
	}

	@Then("^I should be logged in$")
	public void i_should_be_logged_in() throws Throwable {
		System.out.println("I am logged on to the website");
	}

	@Given("^User search for Lenovo Laptop$")
	public void user_searched_for_Lenovo_Laptop() throws Throwable {
		System.out.println("User searched for Lenovo Laptop");
	}

	@When("^Add the first laptop that appears in the search result to the basket$")
	public void add_the_first_laptop_that_appears_in_the_search_result_to_the_basket() throws Throwable {
		System.out.println("First search result added to bag");
	}

	@Then("^User basket should display with added item$")
	public void user_basket_should_display_with_item() throws Throwable {
		System.out.println("Bag is now contains the added product");
	}

	@Given("^User navigate for Lenovo Laptop$")
	public void user_navigate_for_Lenovo_Laptop() throws Throwable {
		System.out.println("User navigated for Lenovo Laptop");
	}

	@When("^Add the laptop to the basket$")
	public void add_the_laptop_to_the_basket() throws Throwable {
		System.out.println("Laptop added to the basket");
	}
	
	// Hooks
	
	@Before(order=1)
    public void beforeScenario(){
        System.out.println("Start the browser and Clear the cookies");
    }	
	@Before(order=0)
    public void beforeScenarioStart(){
        System.out.println("-----------------Start of Scenario-----------------");
    }	
	
	
	@After(order=0)
    public void afterScenarioFinish(){
        System.out.println("-----------------End of Scenario-----------------");
    }	
	@After(order=1)
    public void afterScenario(){
        System.out.println("Log out the user and close the browser");
    }	
}
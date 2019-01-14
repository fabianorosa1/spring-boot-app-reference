package com.sample.springboot.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.springboot.exception.CustomerNotFoundException;
import com.sample.springboot.persistence.model.Customer;
import com.sample.springboot.persistence.repository.CustomerRepository;
import com.sample.springboot.service.impl.CustomerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void should_add_customer() {
		Customer customer = new Customer("Jack", "Smith");
		this.customerService.addCustomer(customer);
		
		Assert.assertNotNull(customer);
	}
	
	@Test
	public void should_update_customer() {
		Customer customer = new Customer("Jack", "Smith");
		this.customerService.addCustomer(customer);
		
		customer.setFirstName("Martin");
		customer.setLastName("Sith");
		this.customerService.updateCustomer(customer);
		
		Assert.assertNotNull(customer);
	}
	
	@Test(expected = CustomerNotFoundException.class)
	public void should_not_find_customer_byId() {		
		this.customerService.findCustomerById(12345l);
	}
	
	@Test(expected = CustomerNotFoundException.class)
	public void should_delete_customer_byId() {
		this.customerService.deleteCustomer(1l);		
	}
	
	@Test
	public void should_find_all_customers() {		
		this.customerService.findAllCustomers();
	}
}

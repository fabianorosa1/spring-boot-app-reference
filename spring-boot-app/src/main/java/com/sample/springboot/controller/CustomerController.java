package com.sample.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springboot.persistence.model.Customer;
import com.sample.springboot.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author fabiano.a.rosa
 *
 */
@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addCustomer(@RequestBody Customer customer) {
		log.debug("Enter in API addCustomer: {}", customer);

		// add the new transaction
		this.customerService.addCustomer(customer);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateCustomer(@RequestBody Customer customer) {
		log.debug("Enter in API updateCustomer: {}", customer);

		// add the new transaction
		this.customerService.updateCustomer(customer);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable(required = true) Long id) {
		log.debug("Enter in API deleteCustomer: {}", id);

		this.customerService.deleteCustomer(id);
	}

	@GetMapping(value = "/{id}")
	public Customer getCustomerById(@PathVariable(required = true) Long id) {
		log.debug("Enter in API getCustomerById: {}", id);

		// build a DTO with statistics and return it
		return this.customerService.findCustomerById(id);
	}

	@GetMapping
	public List<Customer> getAllCustomerById() {
		log.debug("Enter in API getAllCustomerById");

		// build a DTO with statistics and return it
		return this.customerService.findAllCustomers();
	}
}

package com.sample.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.springboot.exception.CustomerNotFoundException;
import com.sample.springboot.persistence.model.Customer;
import com.sample.springboot.persistence.repository.CustomerRepository;
import com.sample.springboot.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author fabiano.a.rosa
 *
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		log.debug("Enter in addCustomer: {}", customer);
		this.customerRepository.save(customer);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		log.debug("Enter in updateCustomer: {}", customer);
		this.customerRepository.save(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(Long id) {
		log.debug("Enter in deleteCustomer: {}", id);
		
		this.customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));		
		this.customerRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findCustomerById(Long id) {
		log.debug("Enter in findCustomerById: {}", id);
		return this.customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAllCustomers() {
		log.debug("Enter in findAllCustomers");
		return (List<Customer>) this.customerRepository.findAll();
	}
}

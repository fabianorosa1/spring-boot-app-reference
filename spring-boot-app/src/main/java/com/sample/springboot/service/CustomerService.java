package com.sample.springboot.service;

import java.util.List;

import com.sample.springboot.persistence.model.Customer;

/**
 * 
 * @author fabiano.a.rosa
 *
 */
public interface CustomerService {

	/**
	 * 
	 * @param customer
	 */
	public void addCustomer(Customer customer);
	
	/**
	 * 
	 * @param customer
	 */
	public void updateCustomer(Customer customer);
	
	/**
	 * 
	 * @param id
	 */
	public void deleteCustomer(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Customer findCustomerById (Long id);
	
	/**
	 * 
	 * @return
	 */
	public List<Customer> findAllCustomers ();
	
}

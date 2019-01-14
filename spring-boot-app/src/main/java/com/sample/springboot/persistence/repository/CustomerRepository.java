package com.sample.springboot.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sample.springboot.persistence.model.Customer;

/**
 * 
 * @author fabiano.a.rosa
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	/**
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Customer> findByLastName(String lastName);
}

package com.sample.springboot.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.springboot.persistence.model.Customer;
import com.sample.springboot.persistence.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository repository;

	@Test
	public void should_find_no_customers_if_repository_is_empty() {
		Iterable<Customer> customers = repository.findAll();

		assertThat(customers).isEmpty();
	}

	@Test
	public void should_store_a_customer() {
		Customer customer = repository.save(new Customer("Jack", "Smith"));

		assertThat(customer).hasFieldOrPropertyWithValue("firstName", "Jack");
		assertThat(customer).hasFieldOrPropertyWithValue("lastName", "Smith");
	}

	@Test
	public void should_delete_all_customer() {
		entityManager.persist(new Customer("Jack", "Smith"));
		entityManager.persist(new Customer("Adam", "Johnson"));

		repository.deleteAll();

		assertThat(repository.findAll()).isEmpty();
	}

	@Test
	public void should_find_all_customers() {
		Customer customer1 = new Customer("Jack", "Smith");
		entityManager.persist(customer1);

		Customer customer2 = new Customer("Adam", "Johnson");
		entityManager.persist(customer2);

		Customer customer3 = new Customer("Peter", "Smith");
		entityManager.persist(customer3);

		Iterable<Customer> customers = repository.findAll();

		assertThat(customers).hasSize(3).contains(customer1, customer2, customer3);
	}

	@Test
	public void should_find_customer_by_id() {
		Customer customer1 = new Customer("Jack", "Smith");
		entityManager.persist(customer1);

		Customer customer2 = new Customer("Adam", "Johnson");
		entityManager.persist(customer2);

		Customer foundCustomer = repository.findById(customer2.getId()).orElse(null);

		assertThat(foundCustomer).isEqualTo(customer2);
	}

}

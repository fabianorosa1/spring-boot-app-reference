package com.sample.springboot.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author fabiano.a.rosa
 *
 */
@Entity
@Table(name = "customer")
@SequenceGenerator(name = "SEQUENCE_CUSTOMER", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class Customer implements Serializable {
	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CUSTOMER")
	private Long id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
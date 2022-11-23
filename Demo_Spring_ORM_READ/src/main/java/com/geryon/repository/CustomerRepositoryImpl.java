package com.geryon.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.geryon.dto.CustomerDTO;
import com.geryon.entity.Customer;


@Repository(value = "customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public CustomerDTO getCustomer(Integer customerId) {

		CustomerDTO customerDTO = null;
		
		Customer customer = entityManager.find(Customer.class, customerId);
		
		if (customer != null) {
			customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTO.setCustomerType(customer.getCustomerType());
		}
		
		return customerDTO;
		
	}

}

/*
 * NOTE:	
 * 			This class is annotated with @Repository so that Spring automatically
 * 		scans this class and registers it as a Spring Bean. 
 * 			
 * 			The EntityManager object is injected using the @PersistenceContext 
 * 		annotation.
 */
package com.geryon.repository;

import java.time.LocalDate;

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
	
	@Override
	public void addCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		customer.setCustomerType(customerDTO.getCustomerType());
		
		entityManager.persist(customer);
	}

	@Override
	public void updateCustomer(Integer customerId) {

		
		Customer customer = entityManager.find(Customer.class, customerId);
		
		
		if(customer != null) {

			customer.setDateOfBirth(LocalDate.now());
			
			entityManager.persist(customer);
		}
		

	}

}

/*
 * NOTE:	
 * 			This class is annotated with @Repository so that Spring automatically
 * 		scans this class and registers it as a Spring Bean. 
 * 			
 * 			The EntityManager object is injected using the @PersistenceContext 
 * 		annotation.
 * 
 * 			The addCustomer() method creates a new Customer Entity Object in the 
 * 		"NEW/TRANSIENT" state. 
 * 			The persist() method called inside this method is invoked
 * 		to associate a Customer Entity Object with the Persistence Context of the 
 * 		EntityManager which, as a result, changes the Customer Entity Object from 
 * 		"NEW/TRANSIENT" to "MANAGED".
 * 			The values present in the Customer Entity Object will be inserted into 
 * 		the database table when the transaction is committed. If the table already 
 * 		has a row with the dame customerId as this new "Managed" Customer Entity 
 * 		Object, then an EntityExistsException will be thrown. 
 */
package com.geryon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geryon.dto.CustomerDTO;
import com.geryon.exception.GeryonBankException;
import com.geryon.repository.CustomerRepository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public CustomerDTO getCustomer(Integer customerId) throws GeryonBankException {

		
		CustomerDTO customerDTO = customerRepository.getCustomer(customerId);
		
		if(customerDTO == null) {
			throw new GeryonBankException("Service.CUSTOMER_UNAVAILABLE");
		}
		
		return customerDTO;
	}

	@Override
	public void addCustomer(CustomerDTO customerDTO) throws GeryonBankException {

		if(customerRepository.getCustomer(customerDTO.getCustomerId()) != null) {
			throw new GeryonBankException("Service.CUSTOMER_ALREADY_EXISTS");
		}
		
		customerRepository.addCustomer(customerDTO);
		
	}

}

/*
 * NOTE:	
 * 			The @Transactional annotation is added to this service bean because without it, 
 * 		adding a Customer to the database table would throw an TransactionRequiredException.
 * 		"Every Data Manipulation Language (DML) operation needs a database transaction [...]
 * 		To create a database transaction" we annotate the service class with @Transactional 
 */
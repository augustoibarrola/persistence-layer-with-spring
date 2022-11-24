package com.geryon.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geryon.dto.CustomerDTO;
import com.geryon.exception.GeryonBankException;
import com.geryon.repository.CustomerRepository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	public static final Log LOGGER = LogFactory.getLog(CustomerServiceImpl.class);

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
			LOGGER.info("Service.CUSTOMER_ALREADY_EXISTS");
			
			//incorporating temporary fix so test argument
			//in main class doesnt need to be rewritten each time
			
			int newAge = customerRepository.getCustomer(customerDTO.getCustomerId()).getCustomerId();
			customerDTO.setCustomerId(++newAge);
			customerRepository.addCustomer(customerDTO);
			
		} else {			
			customerRepository.addCustomer(customerDTO);
		}
		
		
	}

	@Override
	public void updateCustomer() {
		
		customerRepository.updateCustomer(1);
		

	}

}

/*
 * NOTE:	
 * 			The @Transactional annotation is added to this service bean because without it, 
 * 		adding a Customer to the database table would throw an TransactionRequiredException.
 * 		"Every Data Manipulation Language (DML) operation needs a database transaction [...]
 * 		To create a database transaction" we annotate the service class with @Transactional 
 */
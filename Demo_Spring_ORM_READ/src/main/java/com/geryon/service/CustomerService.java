package com.geryon.service;

import com.geryon.dto.CustomerDTO;
import com.geryon.exception.GeryonBankException;

public interface CustomerService {
	
	public CustomerDTO getCustomer(Integer customerId) throws GeryonBankException;

}

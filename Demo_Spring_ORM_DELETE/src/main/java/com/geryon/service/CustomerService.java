package com.geryon.service;

import com.geryon.dto.CustomerDTO;
import com.geryon.exception.GeryonBankException;

public interface CustomerService {
	
	public CustomerDTO getCustomer(Integer customerId) throws GeryonBankException;
	public void addCustomer(CustomerDTO customerDTO) throws GeryonBankException;
	public void updateCustomer() throws GeryonBankException;
	public void deleteCustomer() throws GeryonBankException;

}

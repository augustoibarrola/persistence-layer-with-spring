package com.geryon.repository;

import com.geryon.dto.CustomerDTO;

public interface CustomerRepository {
	
	public CustomerDTO getCustomer(Integer customerId);
	public void addCustomer(CustomerDTO customerDTO);

}

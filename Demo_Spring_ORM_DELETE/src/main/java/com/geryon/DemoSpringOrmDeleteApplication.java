package com.geryon;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.geryon.dto.CustomerDTO;
import com.geryon.dto.CustomerType;
import com.geryon.exception.GeryonBankException;
import com.geryon.service.CustomerServiceImpl;

@SpringBootApplication
public class DemoSpringOrmDeleteApplication implements CommandLineRunner{

	
	public static final Log LOGGER = LogFactory.getLog(DemoSpringOrmDeleteApplication.class);
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@Autowired
	Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringOrmDeleteApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
//		addCustomer(); //Crud
//		getCustomer(); //cRud
//		updateCustomer(); //crUd
		deleteCustomer(); //cruD
	}

	public void getCustomer() throws GeryonBankException {
		try {
			
			CustomerDTO customerDTO = customerService.getCustomer(1);
			
			LOGGER.info(customerDTO);
		
		} catch (Exception exception) {
		
			
			if(exception.getMessage() != null) LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details"));
		
		}
	}
	
	public void addCustomer() throws GeryonBankException{
		
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setCustomerId(3);
		customerDTO.setEmailId("ricardo@geryon.com");
		customerDTO.setName("Ricardo");
		customerDTO.setDateOfBirth(LocalDate.of(1994, 11, 4));
		customerDTO.setCustomerType(CustomerType.GOLD);
		
		try {
			customerService.addCustomer(customerDTO);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		} catch (Exception exception) {
			if (exception.getMessage() != null) {
				LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details"));
			}
		}
	}
	
	
	public void updateCustomer() {
		
		try {
			
			customerService.updateCustomer();
			
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
			
		} catch (Exception exception) {
			if(exception.getMessage() != null) {
				LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details"));
			}
		}
		
	}
	
	public void deleteCustomer() {
		
	}

}

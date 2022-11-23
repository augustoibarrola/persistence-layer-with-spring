package com.geryon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.geryon.dto.CustomerDTO;
import com.geryon.exception.GeryonBankException;
import com.geryon.service.CustomerServiceImpl;

@SpringBootApplication
public class DemoSpringOrmReadApplication implements CommandLineRunner{

	
	public static final Log LOGGER = LogFactory.getLog(DemoSpringOrmReadApplication.class);
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@Autowired
	Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringOrmReadApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		getCustomer();
	}
	
	public void getCustomer() throws GeryonBankException {
		try {
			
			CustomerDTO customerDTO = customerService.getCustomer(1);
			
			LOGGER.info(customerDTO);
		
		} catch (Exception exception) {
		
			
			if(exception.getMessage() != null) LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details"));
		
		}
	}

}

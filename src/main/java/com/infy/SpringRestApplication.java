package com.infy;

import com.infy.dto.CustomerDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringRestApplication implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(SpringRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	//Spring REST TEMPLATE
	@Override
	public void run(String... args)throws Exception{
		// Consuming GET REST API with RestTemplate
		//getCustomerDetails(1002);

		// Consuming POST REST API with RestTemplate
//		CustomerDTO customerDTO = new CustomerDTO();
//		customerDTO.setCustomerId(1004);
//		customerDTO.setName("Fatima");
//		customerDTO.setEmailId("fatima@infy.com");
//		addCustomer(customerDTO);

		//Consuming PUT REST API with RestTemplate
		CustomerDTO customerDTOS = new CustomerDTO();
		customerDTOS.setCustomerId(1004);
		customerDTOS.setEmailId("fatimahGide@infy.com");
		updateCustomer(customerDTOS);




	}

	// Consuming GET REST API with RestTemplate
	public void getCustomerDetails(Integer customerId){
		String url = "http://localhost:5558/infybank/customers/{customerId}";
		RestTemplate restTemplate = new RestTemplate();
		CustomerDTO customerDTO = restTemplate.getForObject(url, CustomerDTO.class, customerId);
		LOGGER.info(customerDTO);
		LOGGER.info("\n");
	}

	// Consuming POST REST API with RestTemplate
	public void addCustomer (CustomerDTO customer){
		String url = "http://localhost:5558/infybank/customers";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(url, customer, String.class);
		LOGGER.info(response);
		LOGGER.info("\n");
	}

	// Consuming PUT REST API with RestTemplate
	public void updateCustomer(CustomerDTO customerDTO){
		String url = "http://localhost:5558/infybank/customers/{customerId}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, customerDTO, customerDTO.getCustomerId());
		LOGGER.info("Customer updated successfully");
		LOGGER.info("\n");
	}

}

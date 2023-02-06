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
		getCustomerDetails(1002);
	}

	// Consuming GET REST API with RestTemplate
	public void getCustomerDetails(Integer customerId){
		String url = "http://localhost:5558/infybank/customers/{customerId}";
		RestTemplate restTemplate = new RestTemplate();
		CustomerDTO customerDTO = restTemplate.getForObject(url, CustomerDTO.class, customerId);
		LOGGER.info(customerDTO);
		LOGGER.info("\n");
	}




}

package com.infy.api;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;
import com.infy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/infybank")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Environment environment;

    //Read Operations
    @GetMapping(value = "/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws InfyBankException{
        List<CustomerDTO> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping(value ="/customers/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer customerId) throws InfyBankException{
        CustomerDTO customerDTO = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    //Create Operation
    @PostMapping(value = "/customers")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer) throws InfyBankException{
        Integer customerId = customerService.addCustomer(customer);
        String successMessage = environment.getProperty("API.INSERT_SUCCESS") + customerId;

        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }
}

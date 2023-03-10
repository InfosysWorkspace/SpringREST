package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    //Read Operation
    @Override
    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
        Optional<Customer> optional = customerRepository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());

        return customerDTO;

    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws InfyBankException{
        Iterable<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerDTO cust = new CustomerDTO();
            cust.setCustomerId(customer.getCustomerId());
            cust.setName(customer.getName());
            cust.setEmailId(customer.getEmailId());
            cust.setDateOfBirth(customer.getDateOfBirth());

            customerDTOS.add(cust);
        });

        if(customerDTOS.isEmpty()){
            throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
        }

        return customerDTOS;

    }

    //Create Operation
    @Override
    public Integer addCustomer(CustomerDTO customer){
        Customer customerEntity = new Customer();
        customerEntity.setCustomerId(customer.getCustomerId());
        customerEntity.setName(customer.getName());
        customerEntity.setEmailId(customer.getEmailId());
        customerEntity.setDateOfBirth(customer.getDateOfBirth());

        Customer customerEntity2 = customerRepository.save(customerEntity);
        return customerEntity2.getCustomerId();
    }

    //Update Operation
    @Override
    public void updateCustomer(Integer customerId, String emailId) throws InfyBankException{
        Optional<Customer> customer = customerRepository.findById(customerId);
        Customer c = customer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        c.setEmailId(emailId);
    }

    @Override
    public void deleteCustomer(Integer customerId) throws InfyBankException{
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        customerRepository.deleteById(customerId);
    }
}

package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;

import java.util.List;

public interface CustomerService {
    //Read Operation
    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException;

    List<CustomerDTO> getAllCustomers() throws InfyBankException;

    //Create Operation
    public Integer addCustomer(CustomerDTO customer) throws InfyBankException;

    //Update Operation
    public void updateCustomer(Integer customerId, String emailId) throws InfyBankException;
}

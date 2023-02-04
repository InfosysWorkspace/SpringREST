package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;

import java.util.List;

public interface CustomerService {
    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException;

    List<CustomerDTO> getAllCustomers() throws InfyBankException;
}

package com.sm.customerservice.service;

import com.sm.customerservice.dto.CustomerDTO;
import com.sm.customerservice.model.Customer;

import java.util.List;

public interface CustomerService {
    Boolean addCustomer(CustomerDTO customerDto);
    Customer getCustomer(Long customerId);
    List<Customer> getAllCustomer();
    Boolean updateCustomer(Long customerId, CustomerDTO customerDto);
    Boolean deleteCustomer(Long customerId);

}

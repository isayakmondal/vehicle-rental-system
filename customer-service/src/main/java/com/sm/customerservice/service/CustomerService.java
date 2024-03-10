package com.sm.customerservice.service;

import com.sm.customerservice.model.Customer;

import java.util.List;

public interface CustomerService {
    Boolean addCustomer(Customer customer);
    Customer getCustomer(Long customerId);
    List<Customer> getAllCustomer();
    Boolean updateCustomer(Long customerId, Customer updatedCustomer);
    Boolean deleteCustomer(Long customerId);

}

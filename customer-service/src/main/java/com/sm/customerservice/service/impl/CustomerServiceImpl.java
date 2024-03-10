package com.sm.customerservice.service.impl;

import com.sm.customerservice.model.Customer;
import com.sm.customerservice.repository.CustomerRepository;
import com.sm.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Boolean addCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        if(savedCustomer!=null) return true;
        return false;

    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Boolean updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer!=null){
            if(updatedCustomer.getName()!=null) customer.setName(updatedCustomer.getName());
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer!=null){
            customerRepository.delete(customer);
            return true;
        }
        return false;
    }
}

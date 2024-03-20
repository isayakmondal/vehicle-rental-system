package com.sm.customerservice.service.impl;

import com.sm.customerservice.controller.CustomerController;
import com.sm.customerservice.dto.CustomerDTO;
import com.sm.customerservice.model.Customer;
import com.sm.customerservice.repository.CustomerRepository;
import com.sm.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Boolean addCustomer(CustomerDTO customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        Customer savedCustomer = customerRepository.save(customer);
        if (savedCustomer != null) return true;
        return false;

    }

    @Override
    public Customer getCustomer(Long customerId) {
        logger.info("Inside getCustomer service");
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Boolean updateCustomer(Long customerId, CustomerDTO customerDto) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            if (customerDto.getName() != null) customer.setName(customerDto.getName());
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
            return true;
        }
        return false;
    }
}

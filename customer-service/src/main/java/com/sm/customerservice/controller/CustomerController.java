package com.sm.customerservice.controller;

import com.sm.customerservice.model.Customer;
import com.sm.customerservice.service.CustomerService;
import com.sm.customerservice.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer != null) {

            return ResponseEntity.status(HttpStatus.OK).body(customer);

        }
        CustomResponse response = new CustomResponse("Customer Not Found", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        if (customerService.addCustomer(customer)) {

            CustomResponse response = new CustomResponse("Customer Added Successfully", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        CustomResponse response = new CustomResponse("Customer Not Added", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customerList = customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    //Implement Update and Delete mappings
    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer){
        if(customerService.updateCustomer(customerId, customer)){
            CustomResponse response = new CustomResponse("Customer Updated Successfully", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomResponse response = new CustomResponse("Customer could not be updated", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId){
        if(customerService.deleteCustomer(customerId)){
            CustomResponse response = new CustomResponse("Customer Deleted Successfully", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        CustomResponse response = new CustomResponse("Customer could not be deleted", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}

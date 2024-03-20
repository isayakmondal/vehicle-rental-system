package com.sm.reservationservice.client;

import com.sm.reservationservice.external.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer-service")
public interface CustomerClient {
    @GetMapping("/customer/{customerId}")
    Customer getCustomer(@PathVariable Long customerId);
}

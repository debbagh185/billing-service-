package com.example.BillingService.client;

import com.example.BillingService.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.BillingService.entities.Customer;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerServiceClient {
    @GetMapping("/customers/{id}")
	Customer findCustomerById(@PathVariable("id") String id);
    @GetMapping("/customers")
    PagedModel<Customer> findAll();
}

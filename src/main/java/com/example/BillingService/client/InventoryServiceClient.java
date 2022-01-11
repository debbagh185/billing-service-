package com.example.BillingService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.BillingService.entities.Product;

@FeignClient(name="PRODUCT-SERVICE")
public interface InventoryServiceClient {
	 @GetMapping("/products/{id}")
	 Product findProductById(@PathVariable("id") String id);
	 @GetMapping("/products")
	 PagedModel<Product> findAll();
}

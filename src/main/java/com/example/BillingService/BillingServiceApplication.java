package com.example.BillingService;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.example.BillingService.dao.InvoiceRepository;
import com.example.BillingService.dao.ProductItemRepository;
import com.example.BillingService.entities.Invoice;
import com.example.BillingService.entities.ProductItem;
import com.example.BillingService.client.CustomerServiceClient;
import com.example.BillingService.client.InventoryServiceClient;


@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	 public static void main(String[] args) {
	        SpringApplication.run(BillingServiceApplication.class, args);
	    }
	    @Bean
	    CommandLineRunner start(InvoiceRepository invoiceRepository, ProductItemRepository productItemRepository,
								InventoryServiceClient inventoryServiceClient, CustomerServiceClient customerServiceClient) {
	        return args -> {
	            customerServiceClient.findAll().getContent().forEach(customer -> {
					Invoice invoice = new Invoice();
					invoice.setBuillingDate(new Date());
					invoice.setCustomerId(customer.getId());
					invoiceRepository.save(invoice);
					inventoryServiceClient.findAll().getContent().forEach(p -> {
						System.out.println(p);
						productItemRepository.save(new ProductItem(null, null, p.getId(),
								p.getPrice(), (int) (1 + Math.random() * 1000), invoice));
					});
				});
	        };
	    }
		

}

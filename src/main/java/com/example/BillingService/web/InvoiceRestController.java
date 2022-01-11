package com.example.BillingService.web;

import com.example.BillingService.dto.InvoiceRequestDTO;
import com.example.BillingService.dto.InvoiceResponseDTO;
import com.example.BillingService.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.BillingService.dao.InvoiceRepository;
import com.example.BillingService.dao.ProductItemRepository;
import com.example.BillingService.entities.Invoice;
import com.example.BillingService.client.CustomerServiceClient;
import com.example.BillingService.client.InventoryServiceClient;

import java.util.List;

@RestController
public class InvoiceRestController {
	@Autowired private InvoiceService invoiceService;
	@Autowired private ProductItemRepository productItemRepository;
	@Autowired private CustomerServiceClient customerServiceClient; 
	@Autowired private InventoryServiceClient inventoryServiceClient;

	@GetMapping(path = "/invoices")
	public List<InvoiceResponseDTO> invoices(){
		return invoiceService.listInvoices();
	}
	@GetMapping(path = "/invoices/{id}")
	public InvoiceResponseDTO invoice(@PathVariable String id){
		return invoiceService.getInvoice(id);
	}
	@PostMapping(path="/invoices")
	public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
		return invoiceService.newInvoice(invoiceRequestDTO);
	}
	

}

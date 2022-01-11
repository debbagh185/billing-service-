package com.example.BillingService.services;

import com.example.BillingService.client.CustomerServiceClient;
import com.example.BillingService.client.InventoryServiceClient;
import com.example.BillingService.dao.InvoiceRepository;
import com.example.BillingService.dao.ProductItemRepository;
import com.example.BillingService.dto.InvoiceRequestDTO;
import com.example.BillingService.dto.InvoiceResponseDTO;
import com.example.BillingService.entities.Customer;
import com.example.BillingService.entities.Invoice;
import com.example.BillingService.mappers.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired private InvoiceRepository invoiceRepository;
    @Autowired private InvoiceMapper invoiceMapper;
    @Autowired private CustomerServiceClient customerServiceClient;
    @Autowired private InventoryServiceClient inventoryServiceClient;
    @Autowired private ProductItemRepository productItemRepository;

    @Override
    public InvoiceResponseDTO getInvoice(String id) {
        Invoice invoice=invoiceRepository.findById(id).orElse(null);
        if(invoice==null) throw new RuntimeException("Invoice Not found");
        Customer customer=customerServiceClient.findCustomerById(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> listInvoices() {
        List<Invoice> invoices=invoiceRepository.findAll();
        for(Invoice invoice:invoices){
            invoice.setCustomer(customerServiceClient.findCustomerById(invoice.getCustomerId()));
            invoice.getProductsItems().forEach(productItem -> {
                productItem.setProduct(inventoryServiceClient.findProductById(productItem.getProductID()));
            });
        }
        return invoices.stream().map((inv)->invoiceMapper.fromInvoice(inv)).collect(Collectors.toList());
    }

    @Override
    public InvoiceResponseDTO newInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        Customer customer;
        try {
            customer=customerServiceClient.findCustomerById(invoiceRequestDTO.getCustomerId());
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        Invoice invoice=invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setCustomer(customer);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setBuillingDate(new Date());
        Invoice savedInvoice=invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customerServiceClient.findCustomerById(savedInvoice.getCustomerId()));
        return invoiceMapper.fromInvoice(savedInvoice);
    }
}

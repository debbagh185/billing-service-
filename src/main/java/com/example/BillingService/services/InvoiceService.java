package com.example.BillingService.services;

import com.example.BillingService.dto.InvoiceRequestDTO;
import com.example.BillingService.dto.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO getInvoice(String id);
    List<InvoiceResponseDTO> listInvoices();
    InvoiceResponseDTO newInvoice(InvoiceRequestDTO invoiceRequestDTO);
}

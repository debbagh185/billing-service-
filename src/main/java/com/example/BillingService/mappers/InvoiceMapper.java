package com.example.BillingService.mappers;

import com.example.BillingService.dto.InvoiceRequestDTO;
import com.example.BillingService.dto.InvoiceResponseDTO;
import com.example.BillingService.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}

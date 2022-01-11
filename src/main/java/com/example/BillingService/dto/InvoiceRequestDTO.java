package com.example.BillingService.dto;

import com.example.BillingService.entities.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class
InvoiceRequestDTO {
	private String customerId;
	private Collection<ProductItem> productsItems;
}

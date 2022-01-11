package com.example.BillingService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Invoice {
	 @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private String id;
	private Date buillingDate; 
	@OneToMany(mappedBy = "invoice")
	private Collection<ProductItem> productsItems; 
	@Transient
	private Customer customer; 
	private String customerId;
}

package com.example.BillingService.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class ProductItem {
	 @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private String id ;
	 @Transient 
	 private Product product; 
	 private String productID;
	 private double price; 
	 private int quantity; 
	 @ManyToOne
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 private Invoice invoice;
}

package com.example.BillingService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.BillingService.entities.Invoice;
import org.springframework.hateoas.PagedModel;

//@RepositoryRestResource
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    PagedModel<Invoice> findAllByCustomerId(String customerId);
}

package com.baum.canteenApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductWithCustomer {

    @Id
    private String productWithCustomerId;

    @DBRef
    private Product product;

    @DBRef
    private Customer customer;

    public ProductWithCustomer(Product product, Customer customer) {
        this.product = product;
        this.customer = customer;
    }

    public double getPrice() {
        return this.product.getPrice();
    }
    public String getCustomerWalletId(){
       return this.customer.getWallet().getWalletId();

    }
    public Customer getCustomer(){
        return this.customer;

    }

}



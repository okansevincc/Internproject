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
public class AddToCart {
    @Id
    private String addToCartId;

    @DBRef
    private Cart cart;

    @DBRef
    private ProductWithCustomer productWithCustomer;

    public AddToCart(Cart cart, ProductWithCustomer productWithCustomer) {
        this.cart = cart;
        this.productWithCustomer = productWithCustomer;
    }
} 

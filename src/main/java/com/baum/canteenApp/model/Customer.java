package com.baum.canteenApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")
public class Customer  {

    @Id
    private String userId;

    private String email;

    private String password;

    private Role role;

    private String firstName;

    private String lastName;

    @DBRef
    private Wallet wallet;

    @DBRef
    private Cart cart;

    public Customer(String userId, Role role, String firstName, String lastName, Wallet wallet, Cart cart) {
        this.userId = userId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.wallet = wallet;
        this.cart = cart;
    }
}

package com.baum.canteenApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Buy {

    @Id
    private String buyId;


    private LocalDateTime localDateTime;

    @DBRef
    private Customer customer;

    @DBRef
    public List<ProductWithCustomer> productsWithCustomers;

    public Buy(LocalDateTime localDateTime, Customer customer, List<ProductWithCustomer> productsWithCustomers) {
        this.localDateTime = localDateTime;
        this.customer = customer;
        this.productsWithCustomers = productsWithCustomers;
    }

}

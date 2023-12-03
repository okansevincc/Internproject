package com.baum.canteenApp.response;

public record ProductWithCustomerResponse(
    String productWithCustomerId,
    CustomerResponse customer,
    ProductResponse product
) {
    
}

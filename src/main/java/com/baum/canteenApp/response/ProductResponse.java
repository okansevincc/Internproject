package com.baum.canteenApp.response;

public record ProductResponse
        (
                String productId,
                String productName,
                Double price,
                Integer unitInStock,
                CategoryResponse categoryResponse
        ) {
}

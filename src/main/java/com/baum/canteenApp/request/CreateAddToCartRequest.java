package com.baum.canteenApp.request;


import jakarta.validation.constraints.NotBlank;

public record CreateAddToCartRequest
        (
                @NotBlank String cartId,
                @NotBlank String productId,
                @NotBlank String customerId
        ) {
}

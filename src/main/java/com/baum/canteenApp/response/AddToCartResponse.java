package com.baum.canteenApp.response;



public record AddToCartResponse(
        String addToCartId,
        CartResponse cartResponse,
        ProductWithCustomerResponse productWithCustomerResponse) {
}

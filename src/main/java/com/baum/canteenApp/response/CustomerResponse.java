package com.baum.canteenApp.response;

public record CustomerResponse
        (
                String customerId,
                String email,
                String firstName,
                String lastName,
                WalletResponse walletResponse,
                CartResponse cartResponse
        ) {
}

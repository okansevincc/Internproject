package com.baum.canteenApp.request;

import jakarta.validation.constraints.NotBlank;

public record CreateBuyRequest
        (
                @NotBlank String customerId

        ) {
}

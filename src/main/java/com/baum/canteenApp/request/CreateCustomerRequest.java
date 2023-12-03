package com.baum.canteenApp.request;

import jakarta.validation.constraints.NotBlank;



public record CreateCustomerRequest
        (
                @NotBlank String userId,
                @NotBlank String firstName,
                @NotBlank String lastName

        ) {
}

package com.baum.canteenApp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest
        (
                @NotBlank String productName,
                @NotNull Double price,
                @NotNull Integer unitInStock,
                @NotBlank String categoryId
        ) {
}

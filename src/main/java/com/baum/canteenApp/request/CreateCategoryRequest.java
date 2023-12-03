package com.baum.canteenApp.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest
        (
                @NotBlank String categoryName
        ) {
}

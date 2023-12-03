package com.baum.canteenApp.request;

import jakarta.validation.constraints.NotBlank;

public record DeleteProductRequest(@NotBlank String productId) {

}

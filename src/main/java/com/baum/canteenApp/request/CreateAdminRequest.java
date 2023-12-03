package com.baum.canteenApp.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateAdminRequest
        (
                @NotBlank @Email(regexp = ".+@.+\\..+") String email,
                 @NotBlank String password
        ) {
}

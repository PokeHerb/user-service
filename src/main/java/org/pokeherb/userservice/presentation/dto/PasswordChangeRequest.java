package org.pokeherb.userservice.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordChangeRequest(
        @NotBlank
        @Size(min=8, max=15)
        String password,

        @NotBlank
        String confirmPassword
) {}

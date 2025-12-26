package org.pokeherb.userservice.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserRegisterRequest(
        @NotBlank
        @Size(min=4, max=10)
        String username,

        @NotBlank
        @Size(min=8, max=15)
        String password,

        @NotBlank
        String confirmPassword,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        String phone,

        @NotBlank
        String slackId,

        Long hubId,

        UUID vendorId
) {}

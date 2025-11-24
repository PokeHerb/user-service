package org.pokeherb.userservice.presentation.dto;

import jakarta.validation.constraints.Email;

public record UserUpdateRequest(
        String firstName,
        String lastName,
        @Email
        String email,
        String phone
) {}

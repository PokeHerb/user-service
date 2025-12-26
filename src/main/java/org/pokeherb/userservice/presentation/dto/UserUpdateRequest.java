package org.pokeherb.userservice.presentation.dto;

import jakarta.validation.constraints.Email;

import java.util.UUID;

public record UserUpdateRequest(
        String firstName,
        String lastName,
        @Email
        String email,
        String phone,
        String slackId,
        Long hubId,
        UUID vendorId
) {}

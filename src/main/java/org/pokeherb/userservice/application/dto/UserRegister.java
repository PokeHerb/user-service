package org.pokeherb.userservice.application.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRegister(
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        String phone,
        String slackId,
        Long hubId,
        UUID vendorId
) {}

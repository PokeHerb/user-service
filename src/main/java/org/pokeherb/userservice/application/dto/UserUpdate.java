package org.pokeherb.userservice.application.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserUpdate(
        String firstName,
        String lastName,
        String email,
        String phone,
        String slackId,
        Long hubId,
        UUID vendorId
) {}

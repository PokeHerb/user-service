package org.pokeherb.userservice.application.dto;

public record TokenInfo(
        String access_token,
        // 토큰 유효기간 (초 단위)
        int expires_in,
        int refresh_expires_in,
        String refresh_token,
        String token_type
) {}

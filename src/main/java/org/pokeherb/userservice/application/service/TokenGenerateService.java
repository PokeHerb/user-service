package org.pokeherb.userservice.application.service;

import org.pokeherb.userservice.application.dto.TokenInfo;

public interface TokenGenerateService {
    TokenInfo generate(String username, String password);
}

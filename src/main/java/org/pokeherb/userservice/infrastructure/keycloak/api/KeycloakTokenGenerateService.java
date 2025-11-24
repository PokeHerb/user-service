package org.pokeherb.userservice.infrastructure.keycloak.api;

import lombok.RequiredArgsConstructor;
import org.pokeherb.userservice.application.dto.TokenInfo;
import org.pokeherb.userservice.application.service.TokenGenerateService;
import org.pokeherb.userservice.infrastructure.keycloak.config.KeycloakProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(KeycloakProperties.class)
public class KeycloakTokenGenerateService implements TokenGenerateService {

    private final KeycloakProperties properties;

    @Override
    public TokenInfo generate(String username, String password) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        // Content-Type: application/x-www-form-urlencoded;  키=값&키=값
        form.add("grant_type", "password");
        form.add("client_id", properties.clientId());
        form.add("client_secret", properties.clientSecret());
        form.add("username", username);
        form.add("password", password);
        form.add("scope", "openid profile email");
        System.out.println("키클록 설정:" + properties);
        RestClient client = RestClient.create();
        ResponseEntity<TokenInfo> res = client.post()
                .uri(String.format("%s/realms/%s/protocol/openid-connect/token", properties.serverUrl(), properties.realm()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .toEntity(TokenInfo.class);
        if (res.getStatusCode().is2xxSuccessful()) {
            return res.getBody();
        }

        return null;
    }
}

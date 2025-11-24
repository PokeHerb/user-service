package org.pokeherb.userservice.application.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.pokeherb.userservice.application.dto.UserUpdate;
import org.pokeherb.userservice.infrastructure.keycloak.config.KeycloakProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(KeycloakProperties.class)
public class UserUpdateService {
    private final KeycloakProperties properties;
    private final Keycloak keycloak;

    // 회원 정보 변경
    public void update(UUID userId, UserUpdate data) {
        // 현재 사용자 정보 조회
        UserRepresentation user = getUserProfile(userId);

        if (StringUtils.hasText(data.firstName())) {
            user.setFirstName(data.firstName());
        }

        if (StringUtils.hasText(data.lastName())) {
            user.setLastName(data.lastName());
        }

        if (StringUtils.hasText(data.email())) {
            user.setEmail(data.email());
        }

        Map<String, List<String>> attributes = Objects.requireNonNullElseGet(user.getAttributes(), HashMap::new);

        if (StringUtils.hasText(data.phone())) {
            attributes.put("phone", List.of(data.phone()));
        }

        user.setAttributes(attributes);

        // 업데이트 처리
        keycloak.realm(properties.realm()).users().get(userId.toString()).update(user);
    }

    // 사용자 UUID로 키클록 회원 정보 조회
    private UserRepresentation getUserProfile(UUID userId) {
        return keycloak.realm(properties.realm()).users().get(userId.toString()).toRepresentation();
    }
}

package org.pokeherb.userservice.application.service;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.pokeherb.userservice.application.dto.UserRegister;
import org.pokeherb.userservice.infrastructure.keycloak.config.KeycloakProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(KeycloakProperties.class)
public class UserRegisterService {

    private final Keycloak keycloak;
    private final KeycloakProperties properties;

    public void registerUser(UserRegister data) {
        // Keycloak에 사용자 생성
        UsersResource userResource = keycloak.realm(properties.realm()).users();

        // 기본 정보 처리
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());

        // 추가 정보 처리
        Map<String, List<String>> attributes = new HashMap<>();

        if (data.phone() != null && !data.phone().isBlank()) {
            attributes.put("phone", List.of(data.phone()));
        }

        user.setAttributes(attributes);

        // 회원 가입 시키기
        Response response = userResource.create(user);
        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            // 회원 가입 실패 시 키클록 서버에서 응답한 문제 상황에 대한 문구와 함께 예외 발생
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, response.getStatusInfo().getReasonPhrase());
        }


        // 비밀번호 등록 처리

        // 가입 생성된 사용자의 식별자
        String userId = CreatedResponseUtil.getCreatedId(response);

        // 비밀번호 Credential 생성 및 설정
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(data.password());

        userResource.get(userId).resetPassword(credential);


        // 기본 Role 부여
        RoleRepresentation userRoles = keycloak.realm(properties.realm()).roles().get("ROLE_PENDING").toRepresentation();
        userResource.get(userId).roles().realmLevel().add(List.of(userRoles));
    }
}

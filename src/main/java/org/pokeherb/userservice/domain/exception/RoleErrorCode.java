package org.pokeherb.userservice.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.pokeherb.userservice.global.infrastructure.error.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RoleErrorCode implements BaseErrorCode {
    ROLE_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "USER400_10", "변경할 ROLE을 전송해 주세요.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

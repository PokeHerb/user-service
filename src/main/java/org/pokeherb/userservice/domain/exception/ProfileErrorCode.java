package org.pokeherb.userservice.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.pokeherb.userservice.global.infrastructure.error.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProfileErrorCode implements BaseErrorCode {
    INVALID_PHONE_FORMAT(HttpStatus.BAD_REQUEST, "USER400_3", "수정할 전화번호 형식이 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

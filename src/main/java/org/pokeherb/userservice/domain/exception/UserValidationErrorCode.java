package org.pokeherb.userservice.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.pokeherb.userservice.global.infrastructure.error.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserValidationErrorCode implements BaseErrorCode {
    INVALID_PASSWORD_COMPLEXITY(HttpStatus.BAD_REQUEST, "USER400", "비밀번호는 알파벳 대소문자, 숫자, 특수 문자를 포함하여 8자리 이상, 15자리 이하로 입력하세요."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "USER400_1", "비밀번호가 일치하지 않습니다."),
    INVALID_PHONE_FORMAT(HttpStatus.BAD_REQUEST, "USER400_2", "전화번호 형식이 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

package org.pokeherb.hubservice.global.infrastructure;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    // 예외에서 발생한 에러의 상세 내용
    private final BaseErrorCode code;

    public CustomException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}

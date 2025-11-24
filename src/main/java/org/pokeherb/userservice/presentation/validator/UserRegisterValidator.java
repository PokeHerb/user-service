package org.pokeherb.userservice.presentation.validator;

import org.pokeherb.userservice.application.dto.UserRegisterRequest;
import org.pokeherb.userservice.domain.exception.UserValidationErrorCode;
import org.pokeherb.userservice.global.infrastructure.exception.CustomException;
import org.springframework.util.StringUtils;

public class UserRegisterValidator implements PasswordValidator, PhoneValidator{
    public void validate(UserRegisterRequest req) {

        String password = req.password();
        String confirmPassword = req.confirmPassword();

        // 비밀번호 복잡성 체크
        if (!checkAlpha(password, true) || !checkNumber(password) || !checkSpecialChars(password)) {
            throw new CustomException(UserValidationErrorCode.INVALID_PASSWORD_COMPLEXITY);
        }

        // 비밀번호, 비밀번호 확인 일치 여부
        if (!password.equals(confirmPassword)) {
            throw new CustomException(UserValidationErrorCode.PASSWORD_MISMATCH);
        }

        // 전화번호 형식 체크
        String phone = req.phone();
        if (StringUtils.hasText(phone) && !checkPhone(phone)) {
            throw new CustomException(UserValidationErrorCode.INVALID_PHONE_FORMAT);
        }
    }
}

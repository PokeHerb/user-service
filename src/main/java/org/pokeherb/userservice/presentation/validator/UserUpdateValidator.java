package org.pokeherb.userservice.presentation.validator;

import org.pokeherb.userservice.domain.exception.UserValidationErrorCode;
import org.pokeherb.userservice.global.infrastructure.exception.CustomException;
import org.pokeherb.userservice.presentation.dto.PasswordChangeRequest;
import org.pokeherb.userservice.presentation.dto.UserUpdateRequest;
import org.springframework.util.StringUtils;

public class UserUpdateValidator implements PasswordValidator, PhoneValidator {

    // 회원 정보 수정 추가 검증
    public void validateUpdateProfile(UserUpdateRequest req) {
        String phone = req.phone();

        if (StringUtils.hasText(phone) && !checkPhone(phone)) {
            throw new CustomException(UserValidationErrorCode.INVALID_PHONE_FORMAT);
        }
    }

    // 회원 비밀번호 변경 추가 검증
    public void validateChangePassword(PasswordChangeRequest req) {
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
    }
}

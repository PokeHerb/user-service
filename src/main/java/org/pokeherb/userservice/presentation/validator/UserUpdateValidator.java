package org.pokeherb.userservice.presentation.validator;

import org.pokeherb.userservice.domain.exception.ProfileErrorCode;
import org.pokeherb.userservice.global.infrastructure.exception.CustomException;
import org.pokeherb.userservice.presentation.dto.UserUpdateRequest;
import org.springframework.util.StringUtils;

public class UserUpdateValidator implements PasswordValidator, PhoneValidator {

    // 회원 정보 수정 추가 검증
    public void validateUpdateProfile(UserUpdateRequest req) {
        String phone = req.phone();

        if (StringUtils.hasText(phone) && !checkPhone(phone)) {
            throw new CustomException(ProfileErrorCode.INVALID_PHONE_FORMAT);
        }
    }
}

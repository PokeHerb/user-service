package org.pokeherb.userservice.presentation.validator;

public interface PhoneValidator {
    default boolean checkPhone(String phone) {
        /**
         * 전화번호 복잡성 체크
         * 01[016]-0000/000-0000
         * 01[016]-\d{3,4}-\d{4}
         *
         * 1) 숫자만 남기기 (하이픈/공백 제거)
         * 2) 전화번호 정규식 (010/011/016 + 3-4자리 + 4자리)
         * 3) 정규식으로 유효한 번호인지 확인
         */
        phone = phone.replaceAll("\\D", "");
        String pattern = "^01[016]\\d{3,4}\\d{4}$";
        return phone.matches(pattern);
    }
}

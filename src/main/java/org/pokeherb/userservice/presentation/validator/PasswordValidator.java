package org.pokeherb.userservice.presentation.validator;

public interface PasswordValidator {
    /**
     * 알파벳 복잡성 체크
     *  - 1) 대문자 1개 + 소문자 1개 이상 (caseInsensitive=false)
     *  - 2) 대소문자 구분 없이 알파벳 1자 이상 (caseInsensitive=true)
     *
     * @param password
     * @param caseInsensitive
     * @return
     */
    default boolean checkAlpha(String password, boolean caseInsensitive) {
        if (caseInsensitive) {
            // .*: 0이상 아무 문자, [a-zA-Z]+: 알파벳 대소문자 상관없이 1자 이상
            return password.matches(".*[a-zA-Z]+.*");
        }
        // 대문자 1개 이상, 소문자 1개 이상
        return password.matches(".*[a-z]+.*") && password.matches(".*[A-Z]+.*");
    }

    /**
     * 숫자 복잡성 체크
     * [0-9] -> \d
     *
     * @param password
     * @return
     */
    default boolean checkNumber(String password) {
        return password.matches(".*\\d+.*");
    }

    /**
     * 특수 문자 복잡성 체크
     *
     * @param password
     * @return
     */
    default boolean checkSpecialChars(String password) {
        // 숫자, 알파벳, 한글을 제외한 모든 문자
        String pattern = ".*[^0-9a-zA-Zㄱ-ㅎ가-힣]+.*";
        return password.matches(pattern);
    }
}

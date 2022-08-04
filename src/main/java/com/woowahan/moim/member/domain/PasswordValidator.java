package com.woowahan.moim.member.domain;

public class PasswordValidator {
    public static String passwordCheck(String password) {
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,}$")) {
            throw new IllegalArgumentException("비밀번호는 영문 + 숫자 + 특수문자를 포함하여 8자 이상 입력해주세요");
        }
        return password;
    }
}

package com.woowahan.moim.login.domain;

public class TokenRequest {
    private String userId;
    private String password;

    public TokenRequest() {
    }

    public TokenRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

package com.woowahan.moim.login.domain;

public class TokenResponse {
    private final String name;

    public TokenResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

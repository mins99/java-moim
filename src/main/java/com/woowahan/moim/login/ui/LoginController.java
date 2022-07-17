package com.woowahan.moim.login.ui;

import com.woowahan.moim.login.application.LoginService;
import com.woowahan.moim.login.domain.TokenRequest;
import com.woowahan.moim.login.domain.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody TokenRequest request) {
        TokenResponse response = loginService.login(request);
        return ResponseEntity.ok().body(response);
    }
}

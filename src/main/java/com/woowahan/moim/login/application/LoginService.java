package com.woowahan.moim.login.application;

import com.woowahan.moim.common.jwt.TokenProvider;
import com.woowahan.moim.login.domain.TokenRequest;
import com.woowahan.moim.login.domain.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    public LoginService(AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    public TokenResponse login(TokenRequest request) {
        logger.info("login user : {}", request.getUserId());

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        String jwt = tokenProvider.createToken(authentication);

        // 4. 토큰 발급
        return new TokenResponse(jwt);
    }
}

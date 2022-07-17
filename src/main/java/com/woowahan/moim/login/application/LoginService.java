package com.woowahan.moim.login.application;

import com.woowahan.moim.login.domain.TokenRequest;
import com.woowahan.moim.login.domain.TokenResponse;
import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final MemberRepository memberRepository;

    public LoginService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public TokenResponse login(TokenRequest request) {
        logger.info("login user : {}", request.getUserId());

        Member member = memberRepository.findByUserId(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다"));
        member.checkPassword(request.getPassword());

        return new TokenResponse(member.getName());
    }
}

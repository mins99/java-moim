package com.woowahan.moim.member.application;

import com.woowahan.moim.common.jwt.SecurityUtil;
import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.MemberRepository;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository,
                         PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberResponse createOrganizerMember(MemberRequest memberRequest) {
        Member member;
        Optional<Member> savedMember = memberRepository.findByUserId(memberRequest.getUserId());

        if (savedMember.isPresent()) {
            member = savedMember.get();
            member.updateOrganizerInfo(memberRequest.getTeam());
        } else {
            member = memberRepository.save(memberRequest.toOrganizer(passwordEncoder));
        }

        return MemberResponse.of(member);
    }

    @Transactional
    public MemberResponse createParticipantMember(MemberRequest memberRequest) {
        Member member;
        Optional<Member> savedMember = memberRepository.findByUserId(memberRequest.getUserId());

        if (savedMember.isPresent()) {
            member = savedMember.get();
            member.updateParticipantInfo(memberRequest.getRestrictingIngredient(), memberRequest.getInfo());
        } else {
            member = memberRepository.save(memberRequest.toParticipant(passwordEncoder));
        }

        return MemberResponse.of(member);
    }

    @Transactional(readOnly = true)
    public MemberResponse findMemberInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponse::of)
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
    }
}

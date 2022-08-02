package com.woowahan.moim.member.application;

import com.woowahan.moim.common.jwt.SecurityUtil;
import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
import com.woowahan.moim.member.domain.Authority;
import com.woowahan.moim.member.domain.AuthorityRepository;
import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.MemberRepository;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository,
                         AuthorityRepository authorityRepository,
                         PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberResponse createOrganizerMember(MemberRequest memberRequest) {
        Optional<Member> savedMember = memberRepository.findByUserId(memberRequest.getUserId());
        Member member = savedMember.orElseGet(() -> memberRepository.save(memberRequest.toOrganizer(passwordEncoder)));
        member.updateOrganizerInfo(memberRequest.getTeam());
        authorityRepository.save(new Authority("ROLE_ORGANIZER", memberRequest.getUserId()));

        return MemberResponse.toOrganizer(member);
    }

    @Transactional
    public MemberResponse createParticipantMember(MemberRequest memberRequest) {
        Optional<Member> savedMember = memberRepository.findByUserId(memberRequest.getUserId());
        Member member = savedMember.orElseGet(() -> memberRepository.save(memberRequest.toParticipant(passwordEncoder)));
        member.updateParticipantInfo(memberRequest.getRestrictingIngredient(), memberRequest.getInfo());
        authorityRepository.save(new Authority("ROLE_PARTICIPANT", memberRequest.getUserId()));

        return MemberResponse.toParticipant(member);
    }

    @Transactional(readOnly = true)
    public MemberResponse findMemberInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponse::of)
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
    }
}

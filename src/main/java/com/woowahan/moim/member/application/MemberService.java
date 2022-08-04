package com.woowahan.moim.member.application;

import com.woowahan.moim.common.jwt.SecurityUtil;
import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
import com.woowahan.moim.member.application.dto.MemberUpdateRequest;
import com.woowahan.moim.member.application.dto.OrganizerMemberRequest;
import com.woowahan.moim.member.application.dto.ParticipantMemberRequest;
import com.woowahan.moim.member.domain.Authority;
import com.woowahan.moim.member.domain.AuthorityRepository;
import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.MemberRepository;
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
        isExistMemberCheck(memberRequest.getUserId());

        Member savedMember = memberRepository.save(memberRequest.toOrganizer(passwordEncoder));
        authorityRepository.save(new Authority("ROLE_ORGANIZER", memberRequest.getUserId()));

        return MemberResponse.toOrganizer(savedMember);
    }

    @Transactional
    public void updateOrganizerMember(OrganizerMemberRequest memberRequest) {
        Member savedMember = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));

        isExistAuthorityCheck(savedMember.getUserId(), "ROLE_ORGANIZER");

        savedMember.updateOrganizerInfo(memberRequest.getTeam());
        authorityRepository.save(new Authority("ROLE_ORGANIZER", savedMember.getUserId()));
    }

    @Transactional
    public MemberResponse createParticipantMember(MemberRequest memberRequest) {
        isExistMemberCheck(memberRequest.getUserId());

        Member savedMember = memberRepository.save(memberRequest.toParticipant(passwordEncoder));
        authorityRepository.save(new Authority("ROLE_PARTICIPANT", memberRequest.getUserId()));

        return MemberResponse.toParticipant(savedMember);
    }

    @Transactional
    public void updateParticipantMember(ParticipantMemberRequest memberRequest) {
        Member savedMember = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));

        isExistAuthorityCheck(savedMember.getUserId(), "ROLE_PARTICIPANT");

        savedMember.updateParticipantInfo(memberRequest.getRestrictingIngredient(), memberRequest.getInfo());
        authorityRepository.save(new Authority("ROLE_PARTICIPANT", savedMember.getUserId()));
    }

    @Transactional
    public void updateMemberInfo(MemberUpdateRequest memberRequest) {
        Member savedMember = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
        savedMember.updateMemberInfo(memberRequest.of(passwordEncoder));
    }

    @Transactional(readOnly = true)
    public MemberResponse findMemberInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponse::of)
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
    }

    private void isExistMemberCheck(String userId) {
        boolean isExist = memberRepository.existsByUserId(userId);
        if (isExist) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }

    private void isExistAuthorityCheck(String userId, String role) {
        boolean isExist = authorityRepository.existsByUserIdAndAuthorityName(userId, role);
        if (isExist) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }
}

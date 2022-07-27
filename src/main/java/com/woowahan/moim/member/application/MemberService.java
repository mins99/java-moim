package com.woowahan.moim.member.application;

import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.MemberRepository;
import com.woowahan.moim.member.domain.MemberType;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse createOrganizerMember(MemberRequest memberRequest) {
        Member member;
        Optional<Member> savedMember = memberRepository.findByUserId(memberRequest.getUserId());

        if (savedMember.isPresent()) {
            member = savedMember.get();
            member.updateOrganizerInfo(memberRequest.getTeam());
        } else {
            member = memberRepository.save(memberRequest.toOrganizer());
        }

        return MemberResponse.of(member, MemberType.ORGANIZER);
    }

    @Transactional
    public MemberResponse createParticipantMember(MemberRequest memberRequest) {
        Member member;
        Optional<Member> savedMember = memberRepository.findByUserId(memberRequest.getUserId());

        if (savedMember.isPresent()) {
            member = savedMember.get();
            member.updateParticipantInfo(memberRequest.getRestrictingIngredient(), memberRequest.getInfo());
        } else {
            member = memberRepository.save(memberRequest.toParticipant());
        }

        return MemberResponse.of(member, MemberType.PARTICIPANT);
    }
}

package com.woowahan.moim.member.application;

import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.MemberRepository;
import com.woowahan.moim.member.domain.Organizer;
import com.woowahan.moim.member.domain.OrganizerRepository;
import com.woowahan.moim.member.domain.Participant;
import com.woowahan.moim.member.domain.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final OrganizerRepository organizerRepository;
    private final ParticipantRepository participantRepository;

    public MemberService(MemberRepository memberRepository,
                         OrganizerRepository organizerRepository,
                         ParticipantRepository participantRepository) {
        this.memberRepository = memberRepository;
        this.organizerRepository = organizerRepository;
        this.participantRepository = participantRepository;
    }

    @Transactional
    public MemberResponse createOrganizerMember(MemberRequest memberRequest) {
        Organizer savedOrganizer = organizerRepository.save(memberRequest.toOrganizer());
        Member savedMember = memberRepository.save(memberRequest.toMember(savedOrganizer));
        return MemberResponse.of(savedMember);
    }

    @Transactional
    public MemberResponse createParticipantMember(MemberRequest memberRequest) {
        Participant savedParticipant = participantRepository.save(memberRequest.toParticipant());
        Member savedMember = memberRepository.save(memberRequest.toMember(savedParticipant));
        return MemberResponse.of(savedMember);
    }
}

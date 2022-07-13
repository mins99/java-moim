package com.woowahan.moim.member.ui;

import com.woowahan.moim.member.application.MemberService;
import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/organizer")
    public ResponseEntity createOrganizerMember(@RequestBody MemberRequest request) {
        MemberResponse member = memberService.createOrganizerMember(request);
        return ResponseEntity.created(URI.create("/members/" + member.getId())).build();
    }

    @PostMapping("/members/participant")
    public ResponseEntity createParticipantMember(@RequestBody MemberRequest request) {
        MemberResponse member = memberService.createParticipantMember(request);
        return ResponseEntity.created(URI.create("/members/" + member.getId())).build();
    }
}

package com.woowahan.moim.member.ui;

import com.woowahan.moim.member.application.MemberService;
import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
import com.woowahan.moim.member.application.dto.OrganizerMemberRequest;
import com.woowahan.moim.member.application.dto.ParticipantMemberRequest;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/organizer")
    public ResponseEntity<MemberResponse> createOrganizerMember(@RequestBody MemberRequest request) {
        MemberResponse member = memberService.createOrganizerMember(request);
        return ResponseEntity.created(URI.create("/members/" + member.getId())).build();
    }

    @PutMapping("/organizer")
    public ResponseEntity<MemberResponse> updateOrganizerMember(@RequestBody OrganizerMemberRequest request) {
        memberService.updateOrganizerMember(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/participant")
    public ResponseEntity<MemberResponse> createParticipantMember(@RequestBody MemberRequest request) {
        MemberResponse member = memberService.createParticipantMember(request);
        return ResponseEntity.created(URI.create("/members/" + member.getId())).build();
    }

    @PutMapping("/participant")
    public ResponseEntity<MemberResponse> updateParticipantMember(@RequestBody ParticipantMemberRequest request) {
        memberService.updateParticipantMember(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> findMemberInfo() {
        return ResponseEntity.ok(memberService.findMemberInfo());
    }

    @PutMapping("/me")
    public ResponseEntity<MemberResponse> updateMemberInfo(@RequestBody MemberRequest request) {
        memberService.updateMemberInfo(request);
        return ResponseEntity.ok().build();
    }
}

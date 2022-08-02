package com.woowahan.moim.member.ui;

import com.woowahan.moim.member.application.MemberService;
import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
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
    public ResponseEntity createOrganizerMember(@RequestBody MemberRequest request) {
        MemberResponse member = memberService.createOrganizerMember(request);
        return ResponseEntity.created(URI.create("/members/" + member.getId())).build();
    }

    @PutMapping("/organizer")
    public ResponseEntity updateOrganizerMember(@RequestBody MemberRequest request) {
        memberService.createOrganizerMember(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/participant")
    public ResponseEntity createParticipantMember(@RequestBody MemberRequest request) {
        MemberResponse member = memberService.createParticipantMember(request);
        return ResponseEntity.created(URI.create("/members/" + member.getId())).build();
    }

    @PutMapping("/participant")
    public ResponseEntity updateParticipantMember(@RequestBody MemberRequest request) {
        memberService.createParticipantMember(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> findMemberInfo() {
        return ResponseEntity.ok(memberService.findMemberInfo());
    }

    @PutMapping("/me")
    public ResponseEntity updateMemberInfo(@RequestBody MemberRequest request) {
        memberService.updateMemberInfo(request);
        return ResponseEntity.ok().build();
    }
}

package com.woowahan.moim.member.application.dto;

import com.woowahan.moim.member.domain.Member;

public class MemberResponse {
    private final long id;

    public MemberResponse(long id) {
        this.id = id;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId());
    }

    public long getId() {
        return id;
    }
}

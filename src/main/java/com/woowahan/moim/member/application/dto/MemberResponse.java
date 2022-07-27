package com.woowahan.moim.member.application.dto;

import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.MemberType;

public class MemberResponse {
    private final long id;
    private final MemberType memberType;

    public MemberResponse(long id, MemberType memberType) {
        this.id = id;
        this.memberType = memberType;
    }

    public static MemberResponse of(Member member, MemberType memberType) {
        return new MemberResponse(member.getId(), memberType);
    }

    public long getId() {
        return id;
    }

    public MemberType getMemberType() {
        return memberType;
    }
}

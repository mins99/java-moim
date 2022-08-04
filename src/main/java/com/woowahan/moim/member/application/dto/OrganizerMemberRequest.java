package com.woowahan.moim.member.application.dto;

public class OrganizerMemberRequest {
    private String team;

    public OrganizerMemberRequest() {
    }

    public OrganizerMemberRequest(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
}

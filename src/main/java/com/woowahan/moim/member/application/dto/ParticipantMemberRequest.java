package com.woowahan.moim.member.application.dto;

public class ParticipantMemberRequest {
    private String restrictingIngredient;
    private String info;

    public ParticipantMemberRequest() {
    }

    public ParticipantMemberRequest(String restrictingIngredient, String info) {
        this.restrictingIngredient = restrictingIngredient;
        this.info = info;
    }

    public String getRestrictingIngredient() {
        return restrictingIngredient;
    }

    public String getInfo() {
        return info;
    }
}

package com.woowahan.moim.member.application.dto;

import com.woowahan.moim.member.domain.Member;
import java.time.LocalDate;
import lombok.Builder;

public class MemberResponse {
    private long id;
    private String name;
    private LocalDate birthday;
    private String gender;
    private String userId;
    private String email;
    private String team;
    private String restrictingIngredient;
    private String info;

    public MemberResponse() {
    }

    @Builder
    private MemberResponse(long id, String name, LocalDate birthday, String gender, String userId, String email,
                          String team, String restrictingIngredient, String info) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.userId = userId;
        this.email = email;
        this.team = team;
        this.restrictingIngredient = restrictingIngredient;
        this.info = info;
    }

    public static MemberResponse toOrganizer(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .birthday(member.getBirthday())
                .gender(member.getGender())
                .userId(member.getUserId())
                .email(member.getEmail())
                .team(member.getTeam())
                .build();
    }

    public static MemberResponse toParticipant(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .birthday(member.getBirthday())
                .gender(member.getGender())
                .userId(member.getUserId())
                .email(member.getEmail())
                .restrictingIngredient(member.getRestrictingIngredient())
                .info(member.getInfo())
                .build();
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .birthday(member.getBirthday())
                .gender(member.getGender())
                .userId(member.getUserId())
                .email(member.getEmail())
                .team(member.getTeam())
                .restrictingIngredient(member.getRestrictingIngredient())
                .info(member.getInfo())
                .build();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getTeam() {
        return team;
    }

    public String getRestrictingIngredient() {
        return restrictingIngredient;
    }

    public String getInfo() {
        return info;
    }
}

package com.woowahan.moim.member.application.dto;

import com.woowahan.moim.member.domain.Member;

public class MemberResponse {
    private long id;
    private String name;
    private String birthday;
    private char gender;
    private String userId;
    private String email;
    private String team;
    private String restrictingIngredient;
    private String info;

    public MemberResponse() {
    }

    public MemberResponse(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.birthday = builder.birthday;
        this.gender = builder.gender;
        this.userId = builder.userId;
        this.email = builder.email;
        this.team = builder.team;
        this.restrictingIngredient = builder.restrictingIngredient;
        this.info = builder.info;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String birthday;
        private char gender;
        private String userId;
        private String email;
        private String team;
        private String restrictingIngredient;
        private String info;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder gender(char gender) {
            this.gender = gender;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder team(String team) {
            this.team = team;
            return this;
        }

        public Builder restrictingIngredient(String restrictingIngredient) {
            this.restrictingIngredient = restrictingIngredient;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public MemberResponse build() {
            return new MemberResponse(this);
        }
    }

    public static MemberResponse toOrganizer(Member member) {
        return new MemberResponse.Builder()
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
        return new Builder()
                .id(member.getId())
                .name(member.getName())
                .birthday(member.getBirthday())
                .gender(member.getGender())
                .userId(member.getUserId())
                .email(member.getEmail())
                .info(member.getInfo())
                .restrictingIngredient(member.getRestrictingIngredient())
                .build();
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse.Builder()
                .id(member.getId())
                .name(member.getName())
                .birthday(member.getBirthday())
                .gender(member.getGender())
                .userId(member.getUserId())
                .email(member.getEmail())
                .team(member.getTeam())
                .info(member.getInfo())
                .restrictingIngredient(member.getRestrictingIngredient())
                .build();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public char getGender() {
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

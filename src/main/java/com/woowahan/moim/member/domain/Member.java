package com.woowahan.moim.member.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birthday;
    private char gender;
    private String userId;
    private String password;
    private String email;
    private String team;
    private String restrictingIngredient;
    private String info;

    protected Member() {
    }

    public Member(Builder builder) {
        this.name = builder.name;
        this.birthday = builder.birthday;
        this.gender = builder.gender;
        this.userId = builder.userId;
        this.password = builder.password;
        this.email = builder.email;
        this.team = builder.team;
        this.restrictingIngredient = builder.restrictingIngredient;
        this.info = builder.info;
    }

    public void updateOrganizerInfo(String team) {
        this.team = team;
    }

    public void updateParticipantInfo(String restrictingIngredient, String info) {
        this.restrictingIngredient = restrictingIngredient;
        this.info = info;
    }

    public void updateMemberInfo(Member member) {
        this.name = member.name;
        this.birthday = member.birthday;
        this.gender = member.gender;
        this.userId = member.userId;
        this.password = member.password;
        this.email = member.email;
        this.team = member.team;
        this.restrictingIngredient = member.restrictingIngredient;
        this.info = member.info;
    }

    public Long getId() {
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

    public String getPassword() {
        return password;
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

    public static class Builder {
        private String name;
        private String birthday;
        private char gender;
        private String userId;
        private String password;
        private String email;
        private String team;
        private String restrictingIngredient;
        private String info;

        public Builder() {
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

        public Builder password(String password) {
            this.password = password;
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

        public Member build() {
            return new Member(this);
        }
    }
}

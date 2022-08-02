package com.woowahan.moim.member.application.dto;

import com.woowahan.moim.member.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberRequest {
    private String name;
    private String birthday;
    private char gender;
    private String userId;
    private String password;
    private String email;
    private String team;
    private String restrictingIngredient;
    private String info;

    public MemberRequest() {
    }

    public MemberRequest(String name, String birthday, char gender, String userId, String password, String email,
                         String team, String restrictingIngredient, String info) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.team = team;
        this.restrictingIngredient = restrictingIngredient;
        this.info = info;
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

    public Member toOrganizer(PasswordEncoder passwordEncoder) {
        return new Member.Builder()
                .name(name)
                .birthday(birthday)
                .gender(gender)
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .team(team)
                .build();
    }

    public Member toParticipant(PasswordEncoder passwordEncoder) {
        return new Member.Builder()
                .name(name)
                .birthday(birthday)
                .gender(gender)
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .info(info)
                .restrictingIngredient(restrictingIngredient)
                .build();
    }

    public Member of(PasswordEncoder passwordEncoder) {
        return new Member.Builder()
                .name(name)
                .birthday(birthday)
                .gender(gender)
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .team(team)
                .info(info)
                .restrictingIngredient(restrictingIngredient)
                .build();
    }
}

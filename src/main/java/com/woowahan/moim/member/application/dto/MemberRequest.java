package com.woowahan.moim.member.application.dto;

import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.Organizer;
import com.woowahan.moim.member.domain.Participant;

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

    public Organizer toOrganizer() {
        return new Organizer(userId, team);
    }

    public Participant toParticipant() {
        return new Participant(userId, restrictingIngredient, info);
    }

    public Member toMember(Organizer organizer) {
        return new Member(name, birthday, gender, userId, password, email, organizer);
    }

    public Member toMember(Participant participant) {
        return new Member(name, birthday, gender, userId, password, email, participant);
    }
}

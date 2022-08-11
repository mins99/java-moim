package com.woowahan.moim.member.application.dto;

import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.Organizer;
import com.woowahan.moim.member.domain.Participant;
import com.woowahan.moim.member.domain.PasswordValidator;
import java.time.LocalDate;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberUpdateRequest {
    private String name;
    private LocalDate birthday;
    private String gender;
    private String password;
    private String email;
    private String team;
    private String restrictingIngredient;
    private String info;

    public MemberUpdateRequest() {
    }

    public MemberUpdateRequest(String name, LocalDate birthday, String gender, String password, String email, String team) {
        this(name, birthday, gender, password, email, team, null, null);
    }

    public MemberUpdateRequest(String name, LocalDate birthday, String gender, String password, String email,
                               String restrictingIngredient, String info) {
        this(name, birthday, gender, password, email, null, restrictingIngredient, info);
    }

    public MemberUpdateRequest(String name, LocalDate birthday, String gender, String password, String email,
                               String team, String restrictingIngredient, String info) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.team = team;
        this.restrictingIngredient = restrictingIngredient;
        this.info = info;
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

    public Member of(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(name)
                .birthday(birthday)
                .gender(gender)
                .password(passwordEncoder.encode(PasswordValidator.passwordCheck(password)))
                .email(email)
                .organizer(new Organizer(team))
                .participant(new Participant(restrictingIngredient, info))
                .build();
    }
}

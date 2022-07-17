package com.woowahan.moim.member.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
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
    @OneToOne
    private Organizer organizer;
    @OneToOne
    private Participant participant;

    protected Member() {
    }

    public Member(String name, String birthday, char gender, String userId, String password, String email,
                  Organizer organizer) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.organizer = organizer;
    }

    public Member(String name, String birthday, char gender, String userId, String password, String email,
                  Participant participant) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.participant = participant;
    }

    public void checkPassword(String password) {
        if (!this.password.equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
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

    public Organizer getOrganizer() {
        return organizer;
    }

    public Participant getParticipant() {
        return participant;
    }


}

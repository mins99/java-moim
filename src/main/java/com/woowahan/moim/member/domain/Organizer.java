package com.woowahan.moim.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userId;
    private String team;
    @OneToOne
    private Member member;

    protected Organizer() {
    }

    public Organizer(String userId, String team) {
        this.userId = userId;
        this.team = team;
    }
}

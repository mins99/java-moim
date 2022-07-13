package com.woowahan.moim.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userId;
    private String restrictingIngredient;
    private String info;
    @OneToOne
    private Member member;

    protected Participant() {
    }

    public Participant(String userId, String restrictingIngredient, String info) {
        this.userId = userId;
        this.restrictingIngredient = restrictingIngredient;
        this.info = info;
    }
}

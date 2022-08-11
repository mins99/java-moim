package com.woowahan.moim.member.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String restrictingIngredient;
    private String info;

    public Participant(String restrictingIngredient, String info) {
        this.restrictingIngredient = restrictingIngredient;
        this.info = info;
    }

    public void updateParticipantInfo(String restrictingIngredient, String info) {
        this.restrictingIngredient = restrictingIngredient;
        this.info = info;
    }
}

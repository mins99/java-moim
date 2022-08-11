package com.woowahan.moim.member.domain;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate birthday;
    @Column(length = 1)
    private String gender;
    private String userId;
    private String password;
    private String email;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Organizer organizer;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Participant participant;

    public void updateOrganizerInfo(String team) {
        this.organizer.updateOrganizerInfo(team);
    }

    public void updateParticipantInfo(String restrictingIngredient, String info) {
        this.participant.updateParticipantInfo(restrictingIngredient, info);
    }

    public void updateMemberInfo(Member member) {
        this.name = member.name;
        this.birthday = member.birthday;
        this.gender = member.gender;
        this.password = member.password;
        this.email = member.email;
        if (this.organizer != null) {
            updateOrganizerInfo(member.organizer.getTeam());
        }
        if (this.participant != null) {
            updateParticipantInfo(member.participant.getRestrictingIngredient(), member.participant.getInfo());
        }
    }

    @Builder
    public Member(String name, LocalDate birthday, String gender, String userId, String password, String email,
                  Organizer organizer, Participant participant) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.organizer = organizer;
        this.participant = participant;
    }

    public String getTeam() {
        return organizer != null ? organizer.getTeam() : null;
    }

    public String getRestrictingIngredient() {
        return participant != null ? participant.getRestrictingIngredient() : null;
    }

    public String getInfo() {
        return participant != null ? participant.getInfo() : null;
    }
}

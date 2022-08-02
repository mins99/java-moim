package com.woowahan.moim.member.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorityName;
    private String userId;

    protected Authority() {
    }

    public Authority(String authorityName, String userId) {
        this.authorityName = authorityName;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public String getUserId() {
        return userId;
    }
}

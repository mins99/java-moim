package com.woowahan.moim.member.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findAllByUserId(String userId);

    boolean existsByUserIdAndAuthorityName(String userId, String authorityName);
}

package com.woowahan.moim.login.application;

import com.woowahan.moim.member.domain.AuthorityRepository;
import com.woowahan.moim.member.domain.Member;
import com.woowahan.moim.member.domain.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;

    public CustomUserDetailsService(MemberRepository memberRepository,
                                    AuthorityRepository authorityRepository) {
        this.memberRepository = memberRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUserId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        List<GrantedAuthority> grantedAuthorities = authorityRepository.findAllByUserId(member.getUserId()).stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                grantedAuthorities
        );
    }
}

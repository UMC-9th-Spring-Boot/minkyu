package com.example.umc_9th.grobal.auth;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.exception.MemberException;
import com.example.umc_9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

     //생성한 UserDetails 검증
    @Override
    public UserDetails loadUserByUsername(
            String username // 이메일
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(member);
    }
}
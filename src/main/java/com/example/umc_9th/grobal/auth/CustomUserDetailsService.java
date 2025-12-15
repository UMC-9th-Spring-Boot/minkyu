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
    //         Authentication authentication = authenticationManager.authenticate(authenticationToken);
    // 이 한 줄 호출 딜 때  CustomUserDetailsService에서 loadUserByUsername 호출해서 사용자 검색
    // 인증 성공 시  CustomUserDetails 반환 인자값으로 Member 객체 담아서
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
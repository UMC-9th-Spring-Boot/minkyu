package com.example.umc_9th.domain.member.service;

import com.example.umc_9th.domain.food.entity.MemberFood;
import com.example.umc_9th.domain.food.exception.FoodException;
import com.example.umc_9th.domain.food.exception.code.FoodErrorCode;
import com.example.umc_9th.domain.food.repository.FoodRepository;
import com.example.umc_9th.domain.food.repository.MemberFoodRepository;
import com.example.umc_9th.domain.member.converter.MemberConverter;
import com.example.umc_9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.res.MemberResDTO;
import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.exception.MemberException;
import com.example.umc_9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.grobal.auth.CustomUserDetails;
import com.example.umc_9th.grobal.auth.JwtUtil;
import com.example.umc_9th.grobal.auth.enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;
    // 인증을 관리하는 중심 컴포넌트
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // 회원가입
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // 멤버 객체 생성
        String salt = passwordEncoder.encode(dto.password());
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // DB 적용
        memberRepository.save(member);
        // 선호 음식 존재 여부 확인
        if (!dto.preferCategory().isEmpty()){
            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .foodCategory(foodRepository.findById(id)  // 변경!
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());
            memberFoodRepository.saveAll(memberFoodList);
        }
        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }


//   1. 실습1

    @Transactional
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {

        // 1. Spring Security 인증 객체 생성
        // UsernamePasswordAuthenticationToken 클래스는  implements를 통해 Authentication를 구현한 객체
        // 인증 전 사용자 정보 담은 객체
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                );

        // 2. 인증 수행
        //AuthenticationManager가 맞는 AuthenticationProvider 찾음
        // 대부적으로 UserDetailsService.loadUserByUsername(email) 호출
        // 커스텀한  CustomUserDetailsService의 loadUserByUsername메서드 실행 DB에서 사용자 정보 조회

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 3. SecurityContext에 저장 → 세션에 자동 저장됨
        //    이 시점에 HttpSession에 인증 정보가 저장되고
        //    클라이언트에게 JSESSIONID 쿠키가 발급됨
        //인증된 authentication객체를 SecurityContext에 저장함 -> HttpSession에도 자동 저장
        // 클라이언트에게 JSESSIONID 쿠키가 발급됨
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // 인증된 authentication 객체에서 사용자 상세정보 뽑아옴
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member member = userDetails.getMember();

        // 응답 DTO로 변환
        return MemberConverter.toLoginDTO(member);
    }


    public void logout() {
        // SecurityContext 초기화
        SecurityContextHolder.clearContext();
    }

    //실습2
//    public MemberResDTO.LoginDTO login(
//            MemberReqDTO.@Valid LoginDTO dto
//    ) {
//
//        // Member 조회
//        Member member = memberRepository.findByEmail(dto.email())
//                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
//
//        // 비밀번호 검증
//        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
//            throw new MemberException(MemberErrorCode.NOT_FOUND);
//        }
//
//        // JWT 토큰 발급용 UserDetails
//        CustomUserDetails userDetails = new CustomUserDetails(member);
//        // 엑세스 토큰 발급
//        String accessToken = jwtUtil.createAccessToken(userDetails);
//
//        // DTO
//        return MemberConverter.toLoginDTO(member, accessToken);
//    }





}

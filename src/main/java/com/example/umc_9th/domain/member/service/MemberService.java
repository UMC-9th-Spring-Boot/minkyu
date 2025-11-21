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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;


    // 회원가입
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto){

        // 멤버 객체 생성
        Member member = MemberConverter.toMember(dto);
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

    //로그인
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto){
        Member member =memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));


        // 임시 토큰 부여
        String acceessToken="임시 엑세스 토큰 ";
        String refreshToken="임시 리프레쉬 토큰";

        // 응답 DTO 생성
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getMemberId())
                .accessToken(acceessToken)
                .refreshToken(refreshToken)
                .loginAt(LocalDateTime.now())
                .build();
    }


}

package com.example.umc_9th.domain.member.dto.req;

import com.example.umc_9th.domain.member.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String email,              // 추가
            String password,           // 추가
            String phoneNumber,
            String name,
            Gender gender,
            LocalDate birthDate,
            String address,
            boolean status,
            // 선호 음식 카테고리
            List<Long>preferCategory
    ){}

    public record LoginDTO(
            String email,
            String password
    ){}

}
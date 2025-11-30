package com.example.umc_9th.domain.member.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

    //실습1
     @Builder
    public record LoginDTO(
            Long memberId,
            String name,
            String email,
            String role
    ){}


    //실습2
//    @Builder
//    public record LoginDTO(
//            Long memberId,
//            String accessToken
//    ){}



}

package com.example.umc_9th.domain.member.converter;

import com.example.umc_9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.res.MemberResDTO;
import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.grobal.auth.enums.Role;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.JoinDTO dto,String password,Role role) {
        return Member.builder()
                .email(dto.email())
                .password(password)
                .phoneNumber(dto.phoneNumber())
                .name(dto.name())
                .role(role)
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .address(dto.address())
                .status(dto.status())
                .points(0)
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getMemberId())
                .accessToken(accessToken)  // JWT 토큰 추가
                .build();
    }
//    public static MemberResDTO.LoginDTO toLoginDTO(Member member) {
//        return MemberResDTO.LoginDTO.builder()
//                .memberId(member.getMemberId())
//                .email(member.getEmail())
//                .name(member.getName())
//                .build();
//    }

    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getMemberId())
                .createAt(member.getCreatedAt())
                .build();
    }
}




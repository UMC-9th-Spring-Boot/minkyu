package com.example.umc_9th.domain.member.converter;

import com.example.umc_9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.res.MemberResDTO;
import com.example.umc_9th.domain.member.entity.Member;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.JoinDTO dto) {
        return Member.builder()
                .email(dto.email())
                .password(dto.password())
                .phoneNumber(dto.phoneNumber())
                .name(dto.name())
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .address(dto.address())
                .status(dto.status())
                .points(0)
                .build();
    }

    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getMemberId())
                .createAt(member.getCreatedAt())
                .build();
    }
}




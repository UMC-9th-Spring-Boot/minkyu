package com.example.umc_9th.domain.member.controller;

import com.example.umc_9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.res.MemberResDTO;
import com.example.umc_9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_9th.domain.member.service.MemberService;
import com.example.umc_9th.grobal.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO dto
    ) {
        return ApiResponse.success(MemberSuccessCode.FOUND, memberService.signup(dto));
    }

    //로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody MemberReqDTO.LoginDTO dto
    ) {
        return ApiResponse.success(MemberSuccessCode.FOUND, memberService.login(dto));
    }

}
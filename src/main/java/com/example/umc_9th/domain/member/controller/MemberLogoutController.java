package com.example.umc_9th.domain.member.controller;


import com.example.umc_9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.res.MemberResDTO;
import com.example.umc_9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_9th.domain.member.service.MemberService;
import com.example.umc_9th.grobal.apiPayload.ApiResponse;
import com.example.umc_9th.grobal.auth.enums.Role;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberLogoutController {

    private final MemberService memberService;



    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    public ApiResponse<String> logout(HttpServletRequest request) {
        memberService.logout();

        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ApiResponse.onSuccess("로그아웃되었습니다.");
    }




}
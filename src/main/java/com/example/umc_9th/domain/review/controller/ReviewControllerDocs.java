package com.example.umc_9th.domain.review.controller;

import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import com.example.umc_9th.grobal.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewControllerDocs {



    @Operation(
            summary = "미션1 내 리뷰 조회",
            description = "회원이 작성한 리뷰를 조회합니다. storeId와 rating으로 필터링할 수 있습니다."
    )
    ApiResponse<List<ReviewResponseDTO.ReviewDTO>> searchMyReview(
            @Parameter(description = "회원 ID", required = true) Long memberId,
            @Parameter(description = "가게 ID (선택)") Long storeId,
            @Parameter(description = "별점 (1-5, 선택)") Integer rating,
            @RequestParam(required = false)Integer page
    );

}

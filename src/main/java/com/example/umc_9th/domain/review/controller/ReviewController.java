package com.example.umc_9th.domain.review.controller;

import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.service.ReviewQueryService;
import com.example.umc_9th.grobal.apiPayload.ApiResponse;
import com.example.umc_9th.grobal.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/members/{memberId}/reviews/search")
    @Operation(
            summary = "내 리뷰 조회",
            description = "회원이 작성한 리뷰를 조회합니다. storeId와 rating으로 필터링할 수 있습니다."
    )
    public ApiResponse<List<ReviewResponseDTO.ReviewDTO>> searchMyReview(
            @Parameter(description = "회원 ID", required = true)
            @PathVariable Long memberId,

            @Parameter(description = "가게 ID (선택)")
            @RequestParam(required = false) Long storeId,

            @Parameter(description = "별점 (1-5, 선택)")
            @RequestParam(required = false) Integer rating
    ) {
        List<ReviewResponseDTO.ReviewDTO> reviewList = reviewQueryService.searchMyReviews(memberId, storeId, rating);

        return ApiResponse.success(GeneralSuccessCode.REVIEWS_FOUND, reviewList);
    }
}
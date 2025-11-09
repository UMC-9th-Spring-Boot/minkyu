package com.example.umc_9th.domain.review.controller;

import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.service.ReviewQueryService;
import com.example.umc_9th.grobal.apiPayload.ApiResponse;
import com.example.umc_9th.grobal.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/members/{memberId}/reviews/search")
    public ApiResponse<List<ReviewResponseDTO>> searchMyReview(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rating
    ) {


        List<ReviewResponseDTO> reviewList = reviewQueryService.searchMyReviews(memberId, storeId, rating);

        return ApiResponse.success(GeneralSuccessCode.REVIEWS_FOUND, reviewList);
    }

}

package com.example.umc_9th.domain.review.controller;

import com.example.umc_9th.domain.review.converter.ReviewConverter;
import com.example.umc_9th.domain.review.dto.req.ReviewRequestDTO.CreateReviewDTO;
import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO.CreateReviewResultDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.service.ReviewService;
import com.example.umc_9th.grobal.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
@Tag(name = "Review")
public class Review7thController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "리뷰 작성")
    public ApiResponse<CreateReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @Valid @RequestBody CreateReviewDTO request
    ) {
        Review review = reviewService.createReview(storeId, memberId, request);
        return ApiResponse.onSuccess(
                ReviewConverter.toCreateReviewResultDTO(review, request.getImageUrls())
        );
    }




}
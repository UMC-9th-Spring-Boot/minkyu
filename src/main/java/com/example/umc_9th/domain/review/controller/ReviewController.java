package com.example.umc_9th.domain.review.controller;

import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc_9th.domain.review.service.query.ReviewQueryService;
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
public class ReviewController implements ReviewControllerDocs{

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ){
        ReviewSuccessCode code=ReviewSuccessCode.FOUND;

        return ApiResponse.success(code,null);
    }

//    @GetMapping("/reviews/search")
//    public List<Review>searchReview(@RequestParam String filter, @RequestParam String type)throws Exception{
//        // 서비스 요청
//        //List<Review>result=reviewQueryService.searchReview(filter,type);
//        //return result;
//
//    }

// 1. 내가 작성한 리뷰 목록
    @Override
    @GetMapping("/members/{memberId}/reviews/search")
    public ApiResponse<List<ReviewResponseDTO.ReviewDTO>> searchMyReview(
            @Parameter( required = true)
            @PathVariable Long memberId,

            @Parameter()
            @RequestParam(required = false) Long storeId,

            @Parameter()
            @RequestParam(required = false) Integer rating,

            @Parameter()
            @RequestParam(required = false)Integer page

    ) {
        List<ReviewResponseDTO.ReviewDTO> reviewList = reviewQueryService.searchMyReviews(memberId, storeId, rating,page);

        return ApiResponse.success(GeneralSuccessCode.REVIEWS_FOUND, reviewList);
    }

}
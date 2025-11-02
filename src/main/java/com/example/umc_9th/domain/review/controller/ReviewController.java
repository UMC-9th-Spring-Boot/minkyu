package com.example.umc_9th.domain.review.controller;

import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.service.ReviewQueryService;
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
    public List<Review> searchMyReview(
            //  경로에서 memberId를 받음
            @PathVariable Long memberId,

            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rating
    ) {
        // 서비스로 memberId를 포함하여 전달
        return reviewQueryService.searchMyReviews(memberId, storeId, rating);
    }

}

package com.example.umc_9th.domain.review.service;

import com.example.umc_9th.domain.review.dto.req.ReviewRequestDTO;
import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;

public interface ReviewService {
    Review createReview(Long storeId, Long memberId, ReviewRequestDTO.CreateReviewDTO request);
    public ReviewResponseDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    );
}
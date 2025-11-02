package com.example.umc_9th.domain.review.converter;

import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO toReviewDTO(Review review) {


        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .memberName(review.getMember().getName())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }

    //  Review 엔티티 리스트르르 DTO리스트로 변환
    public static List<ReviewResponseDTO> toReviewDTOList(List<Review> reviewList) {
        return reviewList.stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());
    }
}
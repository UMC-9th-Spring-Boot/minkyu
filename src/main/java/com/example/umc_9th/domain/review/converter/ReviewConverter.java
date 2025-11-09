package com.example.umc_9th.domain.review.converter;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.review.dto.req.ReviewRequestDTO;
import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.entity.ReviewImage;
import com.example.umc_9th.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // 리뷰 조회용 DTO 변환
    public static ReviewResponseDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResponseDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .memberName(review.getMember().getName())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    //  Review 엔티티 리스트르르 DTO리스트로 변환
    public static List<ReviewResponseDTO.ReviewDTO> toReviewDTOList(List<Review> reviewList) {
        return reviewList.stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());
    }

    public static Review toReview(ReviewRequestDTO.CreateReviewDTO request, Member member, Store store) {
        return Review.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewImage toReviewImage(String imageUrl, Review review) {
        return ReviewImage.builder()
                .imageUrl(imageUrl)
                .review(review)
                .build();
    }

    public static List<ReviewImage> toReviewImageList(List<String> imageUrls, Review review) {
        if (imageUrls == null || imageUrls.isEmpty()) {
            return List.of();
        }

        return imageUrls.stream()
                .map(url -> toReviewImage(url, review))
                .collect(Collectors.toList());
    }

    public static ReviewResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review, List<String> imageUrls) {
        return ReviewResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getMemberId())
                .storeId(review.getStore().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .imageUrls(imageUrls != null ? imageUrls : List.of())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
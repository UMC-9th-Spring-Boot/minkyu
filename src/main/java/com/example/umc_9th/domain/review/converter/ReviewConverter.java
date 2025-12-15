package com.example.umc_9th.domain.review.converter;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.review.dto.req.ReviewRequestDTO;
import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.entity.ReviewImage;
import com.example.umc_9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    // 페이지 사용 컨버터
    //
    public static ReviewResponseDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    //getMember 불러올 수 있는 이유?
    //JPA가 테이블의 연관관계 파악하고 연관성 있음 불러올 수 있는 기능 제공
    public static ReviewResponseDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDateTime.from(review.getCreatedAt()))
                .build();
    }


}
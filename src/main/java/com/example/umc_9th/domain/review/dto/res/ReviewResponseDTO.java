package com.example.umc_9th.domain.review.dto.res;

import com.example.umc_9th.grobal.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;



public class ReviewResponseDTO extends BaseEntity {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 작성 응답")
    public static class CreateReviewResultDTO {

        @Schema(description = "리뷰 ID")
        private Long reviewId;

        @Schema(description = "작성자 ID")
        private Long memberId;

        @Schema(description = "가게 ID")
        private Long storeId;

        @Schema(description = "별점")
        private Integer rating;

        @Schema(description = "리뷰 내용")
        private String content;

        // 일대다 한 리뷰에 여러 이미지
        @Schema(description = "리뷰 이미지 URL 리스트")
        private List<String> imageUrls;

        @Schema(description = "작성일시")
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 조회 응답")
    public static class ReviewDTO {

        @Schema(description = "리뷰 ID")
        private Long reviewId;

        @Schema(description = "작성자 이름")
        private String memberName;

        @Schema(description = "가게 이름")
        private String storeName;

        @Schema(description = "별점")
        private Integer rating;

        @Schema(description = "리뷰 내용")
        private String content;

        @Schema(description = "작성일시")
        private LocalDateTime createdAt;
    }
    // 워크북 Chapter9
    // recordㄴ나 static class 로 두어서 DTO 정리
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            // 주인이름
            String ownerNickname,
            //평점
            int score,
            //리뷰내용
            String body,
            //생성 일자
            LocalDateTime createdAt
    ){}

}
package com.example.umc_9th.domain.review.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게에 리뷰 추가하기 API")
    public static class CreateReviewDTO {

        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
        @Schema(description = "별점 1~5", required = true)
        private Integer rating;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(min = 10, max = 255, message = "리뷰 내용은 10자 이상 255자 이하여야 합니다.")
        @Schema(description = "리뷰 내용", required = true)
        private String content;

        @Schema(description = "리뷰 이미지 URL 리스트")
        private List<@URL(message = "올바른 URL 형식이 아닙니다.") String> imageUrls;
    }




}
package com.example.umc_9th.domain.review.dto.res;

import com.example.umc_9th.grobal.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO extends BaseEntity {

    private Long reviewId;

    private String memberName;

    private String storeName;

    private Integer rating;


    private String content;



}
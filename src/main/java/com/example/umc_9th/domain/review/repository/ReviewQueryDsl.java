package com.example.umc_9th.domain.review.repository;

import com.example.umc_9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    //api 내 리뷰 보기
  List<Review>findMyReviews(Long memberId, Long storeId,Integer rating );



}

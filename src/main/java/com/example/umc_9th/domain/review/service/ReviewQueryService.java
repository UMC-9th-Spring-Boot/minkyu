package com.example.umc_9th.domain.review.service;

import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchMyReviews(Long memberId, Long storeId, Integer rating) {
        return reviewRepository.findMyReviews(memberId, storeId, rating);
    }

}

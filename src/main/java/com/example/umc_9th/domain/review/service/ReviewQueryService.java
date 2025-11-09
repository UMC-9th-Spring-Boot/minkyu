package com.example.umc_9th.domain.review.service;

import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.domain.review.converter.ReviewConverter;
import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.exception.ReviewException;
import com.example.umc_9th.domain.review.exception.code.ReviewErrorCode;
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
    private final MemberRepository memberRepository;

    public List<ReviewResponseDTO> searchMyReviews(Long memberId, Long storeId, Integer rating) {


        // 회원 유효성 검사
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        // 평점값 유효성 검사
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING_VALUE);
        }

        List<Review> reviewList = reviewRepository.findMyReviews(memberId, storeId, rating);

        return ReviewConverter.toReviewDTOList(reviewList);

    }
}

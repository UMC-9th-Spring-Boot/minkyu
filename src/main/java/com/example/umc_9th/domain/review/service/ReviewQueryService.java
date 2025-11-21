package com.example.umc_9th.domain.review.service;

import com.example.umc_9th.domain.member.entity.Member;
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
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public List<ReviewResponseDTO.ReviewDTO> searchMyReviews(Long memberId, Long storeId, Integer rating) {

        // 회원 유효성 검사 및 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        // 평점값 유효성 검사
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING_VALUE);
        }

        // 모든 리뷰 조회 후 필터링 (기존 Repository 메서드 활용)
        List<Review> reviewList = reviewRepository.findAll();

        // memberId로 필터링
        reviewList = reviewList.stream()
                .filter(review -> review.getMember().getMemberId().equals(memberId))
                .toList();

        return ReviewConverter.toReviewDTOList(reviewList);
    }
}
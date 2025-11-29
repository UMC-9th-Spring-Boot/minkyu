package com.example.umc_9th.domain.review.service.query;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.domain.review.converter.ReviewConverter;
import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.exception.ReviewException;
import com.example.umc_9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc_9th.domain.review.repository.ReviewRepository;
import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.domain.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public List<ReviewResponseDTO.ReviewDTO> searchMyReviews(Long memberId, Long storeId, Integer rating,Integer page) {

        // 페이지 파라미터 검증
        if (page == null || page < 0) {
            page = 0;
        }

        // 회원 유효성 검사 및 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        // 평점값 유효성 검사
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING_VALUE);
        }

        // TODO: 페이징 처리 개선 필요 - Repository에 적절한 메서드 추가 후 PageRequest 사용
        // PageRequest pageRequest = PageRequest.of(page, 5);

        // 모든 리뷰 조회 후 필터링 (기존 Repository 메서드 활용)
        List<Review> reviewList = reviewRepository.findAll();

        // memberId로 필터링
        reviewList = reviewList.stream()
                .filter(review -> review.getMember().getMemberId().equals(memberId))
                .toList();

        return ReviewConverter.toReviewDTOList(reviewList);
    }
    
//    //리뷰 조회 로직
//    List<Review>searchReview(String filter, String type)throws ReviewException {
//
//    }
//
    
}
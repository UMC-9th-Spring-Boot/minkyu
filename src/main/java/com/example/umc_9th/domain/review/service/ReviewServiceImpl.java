package com.example.umc_9th.domain.review.service;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.domain.review.converter.ReviewConverter;
import com.example.umc_9th.domain.review.dto.req.ReviewRequestDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.entity.ReviewImage;
import com.example.umc_9th.domain.review.repository.ReviewImageRepository;
import com.example.umc_9th.domain.review.repository.ReviewRepository;
import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review createReview(Long storeId, Long memberId, ReviewRequestDTO.CreateReviewDTO request) {
        // 1. Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 2. Store 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        // 3. Review 생성 및 저장
        Review review = ReviewConverter.toReview(request, member, store);
        Review savedReview = reviewRepository.save(review);

        // 4. ReviewImage 생성 및 저장
        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            List<ReviewImage> reviewImages = ReviewConverter.toReviewImageList(request.getImageUrls(), savedReview);
            reviewImageRepository.saveAll(reviewImages);
        }

        return savedReview;
    }
}

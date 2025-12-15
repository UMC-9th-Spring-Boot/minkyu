package com.example.umc_9th.domain.review.service;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.domain.review.converter.ReviewConverter;
import com.example.umc_9th.domain.review.dto.req.ReviewRequestDTO;
import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.entity.ReviewImage;
import com.example.umc_9th.domain.review.repository.ReviewImageRepository;
import com.example.umc_9th.domain.review.repository.ReviewRepository;
import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.domain.store.exception.StoreException;
import com.example.umc_9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc_9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public ReviewResponseDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

}

package com.example.umc_9th.domain.review.repository;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.review.converter.ReviewConverter;
import com.example.umc_9th.domain.review.dto.res.ReviewResponseDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.domain.store.exception.StoreException;
import com.example.umc_9th.domain.store.exception.code.StoreErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>,ReviewQueryDsl {



    //2. 리뷰 작성하는 쿼리,
    //* 사진의 경우는 일단 배제
    //(메서드 생성 방식 권장)
    // id로 리뷰 조횔 할 떄 member도 같이 조회
    // 리뷰 조회할 떄 member 엔티티도 같이 가져오게 하는 에노테이션 = @EntityGraph
    @EntityGraph(attributePaths = {"member"})
    Optional<Review> findById(Long id);
    // Member와 Store를 기준으로  Review 조회
    boolean existsByMemberAndStore(Member member, Store store);

    //가게별 조회
    Page<Review> findAllByStore(Store store, Pageable pageable);










}

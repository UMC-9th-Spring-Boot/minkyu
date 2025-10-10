package com.example.umc_9th.domain.review.repository;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.store.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {



    //1리뷰 작성하는 쿼리,
    //* 사진의 경우는 일단 배제
    //(메서드 생성 방식 권장)
    // id로 리뷰 조횔 할 떄 member도 같이 조회
    // 리뷰 조회할 떄 member 엔티티도 같이 가져오게 하는 에노테이션 = @EntityGraph
    @EntityGraph(attributePaths = {"member"})
    Optional<Review> findById(Integer id);
    // Member와 Store를 기준으로  Review 조회
    boolean existsByMemberAndStore(Member member, Store store);



    //2 id 로 멤버 테이블 조회
    //마이 페이지 화면 쿼리
    //(메서드 생성 방식 권장)
    Optional<Member>findById(Long id);








}

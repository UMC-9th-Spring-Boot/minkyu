package com.example.umc_9th.domain.member.repository;

import com.example.umc_9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    //2 id 로 멤버 테이블 조회
    //마이 페이지 화면 쿼리
    //(메서드 생성 방식 권장)
    Optional<Member> findById(Long id);

}
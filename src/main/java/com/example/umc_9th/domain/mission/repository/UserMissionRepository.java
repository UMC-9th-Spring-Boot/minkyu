package com.example.umc_9th.domain.mission.repository;

import com.example.umc_9th.domain.mission.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {


    //3.내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)

    // join fetch : um과 연관된 mission 엔티티 정보까지 가져옴 (N+1) 문제 해결합니다
    @Query(value="SELECT um from UserMission um join fetch um.mission m"+
            // um의 멤버필드에 있는 id가 memgerId이름의 필드를 가져옴
             " where um.member.memberId=:memberId",
    // 페이징 처리를  위해 전체 데이터가 몇 개인지 계산하는 쿼리 지정합니다
    countQuery = " SELECT count(um) FROM UserMission um WHERE um.member.memberId = :memberId")
    Page<UserMission>findMyMission(@Param("memberId") Long memberId, Pageable pageable);



}
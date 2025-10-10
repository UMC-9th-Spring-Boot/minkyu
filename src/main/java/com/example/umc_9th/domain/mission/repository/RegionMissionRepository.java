package com.example.umc_9th.domain.mission.repository;

import com.example.umc_9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegionMissionRepository extends JpaRepository<Mission,Long> {


    //4. 홈 화면 쿼리
    //(현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)


    //1. 미션 테이블 통해서 가게 테이블  -> 지역 테이블 -> 세부지역 테이블 미션이 해당

    // WHERE r.id = :regionId 는 자리표시자로  값 비교
    @Query(value = """
                   SELECT m FROM Mission m
                   JOIN FETCH m.store s 
                   JOIN FETCH s.region r
                   JOIN FETCH r.subRegion sr
                   WHERE r.id = :regionId 
                   """,
            countQuery = "SELECT count(m) FROM Mission m JOIN m.store s WHERE s.region.id = :regionId")
    Page<Mission> findMissionsByRegion(
            @Param("regionId") Long regionId,
            Pageable pageable
    );

}

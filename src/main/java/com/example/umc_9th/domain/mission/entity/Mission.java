package com.example.umc_9th.domain.mission.entity;

import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.store.Store;
import com.example.umc_9th.grobal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = false, length = 50)
    private String title; //미션제목

    @Column(nullable = false, length = 255)
    private String description; //미션설명

    @Column(nullable = false, length = 50)
    private String verificationCode; //미션 인증번호

    @Column(nullable = false)
//    @ColumnDefault("0")
    private int points=0; //미션 포인트

    
    //양방향 고려
//    @OneToMany(fetch = FetchType.LAZY) // 미션 테이블 N:1 관계 매핑
//    @JoinColumn(name = "userMission_id")
//    private List<UserMission> userMissions ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY) // 리뷸 테이블 N:1 관계 매핑
    @JoinColumn(name = "review_id")
    private Review review;



}

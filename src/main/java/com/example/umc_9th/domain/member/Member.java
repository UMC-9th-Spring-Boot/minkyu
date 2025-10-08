package com.example.umc_9th.domain.member;

import com.example.umc_9th.domain.mission.mapping.UserMission;
import com.example.umc_9th.domain.region.Region;
import com.example.umc_9th.domain.region.SubRegion;
import com.example.umc_9th.domain.review.Review;
import com.example.umc_9th.grobal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = false, length = 50)
    private String name; //이름

    @Column(nullable = false, length = 255)
    private String email; //이메일

    @Column(nullable = false, length = 20)
    private String phoneNumber; // 연락처 (VARCHAR(20))

    @Enumerated(EnumType.STRING) // 성별 ENUM 처리
    private Gender gender;

    private LocalDate birthDate; //생년 월일

    @Column(nullable = false)
//    @ColumnDefault("true")
//    private Boolean status; //회원 상태
    private Boolean status = true;

    private Integer points; //포인트

    @Column(length = 255)
    private String address; // 주소 (VARCHAR(255))

    @Column(nullable = false, length = 255)
    private String password; // 비밀번호 (VARCHAR(255))



    //양방향 고려

//    @OneToOne(fetch = FetchType.LAZY) // 동의 테이블과 1:1 관계 매핑
//    @JoinColumn(name = "agree_id")
//    private Agree agree;
//
//    @OneToMany(fetch = FetchType.LAZY) // 리뷰 테이블 N:1 관계 매핑
//    @JoinColumn(name = "review_id")
//    private List<Review> reviews;
//
//    @OneToMany(fetch = FetchType.LAZY) // 미션 테이블 N:1 관계 매핑
//    @JoinColumn(name = "userMission_id")
//    private List<UserMission> userMissions ;
//
////Lazy : 프록시 객체로 채워두고 실제로 그 연관된 엔티티의 데이터를 사용하는 시점
//    @OneToOne(fetch = FetchType.LAZY) // 지역 테이블과 1:1 관계 매핑
//    @JoinColumn(name = "region_id")
//    private Region Region;



}
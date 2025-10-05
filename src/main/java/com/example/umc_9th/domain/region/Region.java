package com.example.umc_9th.domain.region;

import com.example.umc_9th.domain.member.Agree;
import com.example.umc_9th.domain.member.Member;
import com.example.umc_9th.grobal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = false, length = 50)
    private String porvinceName; //도 이름

    @Column(nullable = false, length = 50)
    private String cityName; //시 이름


    @OneToOne(fetch = FetchType.LAZY) // 상세지역 테이블과 1:1 관계 매핑
    @JoinColumn(name = "subRegion_id")
    private SubRegion subRegion;

    @OneToOne(fetch = FetchType.LAZY) // 멤버 테이블과 1:1 관계 매핑
    @JoinColumn(name = "member_id")
    private Member member;



}

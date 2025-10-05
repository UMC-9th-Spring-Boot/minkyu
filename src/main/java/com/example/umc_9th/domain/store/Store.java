package com.example.umc_9th.domain.store;

import com.example.umc_9th.domain.mission.Mission;
import com.example.umc_9th.domain.region.Region;
import com.example.umc_9th.domain.review.ReviewImage;
import com.example.umc_9th.domain.store.mapping.FoodCategory;
import com.example.umc_9th.grobal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk


    @Column(nullable = false, length = 50)
    private String name; //가게 이름



    @OneToMany(fetch = FetchType.LAZY)//미션 테이블과 1:N관계매핑
    @JoinColumn(name="mission_id")
    private List<Mission> missions;

    @OneToOne(fetch = FetchType.LAZY) //FoodCategory 테이블과 1:1 관계 매핑
    @JoinColumn(name = "foodCategory_id")
    private FoodCategory foodCategory;

    @ManyToOne(fetch = FetchType.LAZY)//지역 테이블과 N:1관계매핑
    @JoinColumn(name="region_id")
    private Region region;


}

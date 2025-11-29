package com.example.umc_9th.domain.store.entity;

import com.example.umc_9th.domain.region.entity.Region;
import com.example.umc_9th.domain.food.entity.FoodCategory;
import com.example.umc_9th.grobal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private Integer score; // 가게 평점

//양방향 고려
//    @OneToMany(fetch = FetchType.LAZY)//미션 테이블과 1:N관계매핑
//    @JoinColumn(name="mission_id")
//    private List<Mission> missions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;

    @ManyToOne(fetch = FetchType.LAZY)//지역 테이블과 N:1관계매핑
    @JoinColumn(name="region_id")
    private Region region;





}

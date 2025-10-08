package com.example.umc_9th.domain.store.mapping;


import com.example.umc_9th.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk


    @Column(nullable = false, length = 50)
    private String category; //음식 카테고리


    @OneToOne(fetch = FetchType.LAZY) //Store 테이블과 1:1 관계 매핑
    @JoinColumn(name = "store_id")
    private Store store;

    //양방향 고려
//    @OneToMany(fetch = FetchType.LAZY)//멤버별 푸드 카테고리 테이블과 1:N관계매핑
//    @JoinColumn(name="memberFoodCategory_id")
//    private List<MemberFoodCategory>memberFoodCategory;



}

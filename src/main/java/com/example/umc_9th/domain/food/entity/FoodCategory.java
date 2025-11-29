package com.example.umc_9th.domain.food.entity;


import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.grobal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk


    @Column(nullable = false, length = 50)
    private String category; //음식 카테고리






}

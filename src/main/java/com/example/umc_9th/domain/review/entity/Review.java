package com.example.umc_9th.domain.review.entity;


import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.grobal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = false)
    private int  rating; //별점


    @Column(nullable = false, length = 255)
    private String content; //내용

    @ManyToOne(fetch = FetchType.LAZY) //N:1 관계 매핑
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Store store;

}

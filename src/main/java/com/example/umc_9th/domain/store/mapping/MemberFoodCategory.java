package com.example.umc_9th.domain.store.mapping;


import com.example.umc_9th.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberFoodCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk



    @ManyToOne(fetch = FetchType.LAZY) //N:1 관계 매핑
    @JoinColumn(name = "foodCateGory_id")
    private FoodCategory foodCategory;


    @ManyToOne(fetch = FetchType.LAZY) //N:1 관계 매핑
    @JoinColumn(name = "member_id")
    private Member member;





}

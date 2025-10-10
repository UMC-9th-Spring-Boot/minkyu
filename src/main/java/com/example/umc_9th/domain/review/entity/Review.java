package com.example.umc_9th.domain.review.entity;


import com.example.umc_9th.domain.member.entity.Member;
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





    //양방향 고려
//    @OneToMany(fetch = FetchType.LAZY)//미션 테이블과1 :N관계매핑
//    @JoinColumn(name="mission_id")
//    private List<Mission> missions;
//
//    @OneToMany(fetch = FetchType.LAZY)//리뷰 이미지와 1:N관계매핑
//    @JoinColumn(name="reviewImage_id")
//    private List<ReviewImage> reviewImages;


}

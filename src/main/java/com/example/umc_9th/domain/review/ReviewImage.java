package com.example.umc_9th.domain.review;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = false, length = 255)
    private String  imageUrl; // 이미지 url


    @ManyToOne(fetch = FetchType.LAZY) //N:1 관계 매핑
    @JoinColumn(name = "review_id")
    private Review review;





}

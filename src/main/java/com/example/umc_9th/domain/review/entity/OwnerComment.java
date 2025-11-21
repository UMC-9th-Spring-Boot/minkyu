package com.example.umc_9th.domain.review.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OwnerComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk


    @Column(nullable = false, length = 255)
    private String comment; // 답글

    @ManyToOne(fetch = FetchType.LAZY) //N:1 관계 매핑
    @JoinColumn(name = "review_id")
    private Review review;


}

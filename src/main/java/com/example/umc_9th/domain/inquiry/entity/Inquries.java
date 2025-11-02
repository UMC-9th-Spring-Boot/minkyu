package com.example.umc_9th.domain.inquiry.entity;


import com.example.umc_9th.domain.inquiry.InquriesType;
import com.example.umc_9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = false, length = 50)
    private String title; //제목

    @Column(nullable = false, length = 250)
    private String content; //내용

    @Enumerated(EnumType.STRING) // 문의유형 ENUM 처리
    private InquriesType type;

    @ManyToOne(fetch = FetchType.LAZY) // 리뷸 테이블 N:1 관계 매핑
    @JoinColumn(name = "member_id")
    private Member member;

}

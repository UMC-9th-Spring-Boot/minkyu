package com.example.umc_9th.domain.inquiry;


import com.example.umc_9th.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class inquiryAttachments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = false, length = 250)
    private String url; //문의 사진



    @ManyToOne(fetch = FetchType.LAZY) // 리뷸 테이블 N:1 관계 매핑
    @JoinColumn(name = "member_id")
    private Inquries inquries;


}

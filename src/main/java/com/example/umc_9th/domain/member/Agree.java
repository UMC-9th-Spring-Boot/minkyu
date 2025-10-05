package com.example.umc_9th.domain.member;

import com.example.umc_9th.grobal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Agree extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = true) // null 허용
    @ColumnDefault("false")  // DB의 기본값은 false
    private boolean newEvent; //새로운 이벤트 수신

    @Column(nullable = true)
//    @ColumnDefault("false")
    private boolean answerReview=false; //리뷰 답변

    @Column(nullable = true)
//    @ColumnDefault("false")
    private boolean inquiryContent=false; //문의 내역 답변

    
//    @OneToOne(fetch = FetchType.LAZY) //1:1 관계 매핑
//    @JoinColumn(name = "member_id")
//    private Member member;




}

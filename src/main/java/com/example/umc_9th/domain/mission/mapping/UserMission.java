package com.example.umc_9th.domain.mission.mapping;


import com.example.umc_9th.domain.member.Member;
import com.example.umc_9th.domain.mission.Mission;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk




    @Column(nullable = false)
    @ColumnDefault("true")
    private Boolean status; //미션 상태


    private LocalDateTime completedAt;//미션 완료 일시


    @ManyToOne(fetch = FetchType.LAZY) //N:1 관계 매핑
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) //N:1 관계 매핑
    @JoinColumn(name = "mission_id")
    private Mission mission;





}

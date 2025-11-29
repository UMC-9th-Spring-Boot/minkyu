package com.example.umc_9th.domain.member.entity;

import com.example.umc_9th.domain.food.entity.MemberFood;
import com.example.umc_9th.domain.member.Gender;
import com.example.umc_9th.grobal.BaseEntity;
import com.example.umc_9th.grobal.auth.enums.Role;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId; //pk

    @Column(nullable = false, length = 50)
    private String name; //이름

    @Column(nullable = false, length = 255)
    private String email; //이메일

    @Column(nullable = false, length = 20)
    private String phoneNumber; // 연락처 (VARCHAR(20))

    @Enumerated(EnumType.STRING) // 성별 ENUM 처리
    private Gender gender;

    private LocalDate birthDate; //생년 월일

    @Column(nullable = false)
//    @ColumnDefault("true")
//    private Boolean status; //회원 상태
    private Boolean status = true;

    private Integer points; //포인트

    @Column(length = 255)
    private String address; // 주소 (VARCHAR(255))

    @Column(nullable = false, length = 255)
    private String password; // 비밀번호 (VARCHAR(255))

    @Column(length = 255)
    String accessToken;

    @Column(length = 255)
    String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;




    // 회원의 선호 카테고리 목록 (1:N 관계)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberPreferList = new ArrayList<>();





}
package com.example.umc_9th.domain.mission.service;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.exception.MemberException;
import com.example.umc_9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.domain.mission.entity.Mission;
import com.example.umc_9th.domain.mission.execption.MissionException;
import com.example.umc_9th.domain.mission.execption.code.MissionErrorCode;
import com.example.umc_9th.domain.mission.mapping.UserMission;
import com.example.umc_9th.domain.mission.repository.MissionRepository;
import com.example.umc_9th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final UserMissionRepository userMissionRepository;

    public UserMission challengeMission(Long storeId, Long missionId, Long memberId) {

        // Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // Mission 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 가게 ID 검증
        if (!mission.getStore().getId().equals(storeId)) {
            throw new MissionException(MissionErrorCode.STORE_MISMATCH);
        }

        // 4. 이미 도전 중인지 확인 (status=true가 진행중) , 리포지토리 접근
        boolean isAlreadyChallenging = userMissionRepository
                .existsByMemberAndMissionAndStatus(member, mission, true);

        if (isAlreadyChallenging) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING);
        }

        // 5. UserMission 생성
        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .status(true)  // true = 진행중
                .build();

        return userMissionRepository.save(userMission);
    }




}
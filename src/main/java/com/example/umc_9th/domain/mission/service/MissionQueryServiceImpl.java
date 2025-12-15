package com.example.umc_9th.domain.mission.service;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.exception.MemberException;
import com.example.umc_9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.domain.mission.converter.MissionConverter;
import com.example.umc_9th.domain.mission.dto.res.MissionResponseDTO;
import com.example.umc_9th.domain.mission.entity.Mission;
import com.example.umc_9th.domain.mission.mapping.UserMission;
import com.example.umc_9th.domain.mission.repository.MissionRepository;
import com.example.umc_9th.domain.mission.repository.UserMissionRepository;
import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.domain.store.exception.StoreException;
import com.example.umc_9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc_9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final UserMissionRepository userMissionRepository;


    public MissionResponseDTO.MissionListDTO getMissions(Long storeId, Integer page) {

        // 0. 페이지 파라미터 검증
        if (page == null || page < 0) {
            page = 0;
        }

        // 1. 가게 존재 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 2. 미션 목록 조회
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> missionPage = missionRepository.findAllByStore(store, pageRequest);

        // 3. DTO 변환
        return MissionConverter.toMissionListDTO(missionPage);
    }



    public MissionResponseDTO.MissionListDTO getMyMissions(Long memberId, Integer page) {

        // 0. 페이지 파라미터 검증
        if (page == null || page < 0) {
            page = 0;
        }

        // 1. 멤버 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 2. 진행 중인(true) 미션 목록 조회
        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<UserMission> userMissionPage = userMissionRepository.findAllByMemberAndStatus(
                member,
                true,
                pageRequest
        );

        // 3. 변환 및 반환
        return MissionConverter.toMyMissionListDTO(userMissionPage);
    }


}

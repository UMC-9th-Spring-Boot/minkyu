package com.example.umc_9th.domain.mission.controller;

import com.example.umc_9th.domain.mission.converter.MissionConverter;
import com.example.umc_9th.domain.mission.dto.res.MissionResponseDTO;
import com.example.umc_9th.domain.mission.mapping.UserMission;
import com.example.umc_9th.domain.mission.service.MissionQueryServiceImpl;
import com.example.umc_9th.domain.mission.service.MissionService;
import com.example.umc_9th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc_9th.grobal.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.umc_9th.domain.store.exception.code.StoreSuccessCode.STORE_SUCCESS_CODE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController implements MissionControllerDocs{

    private final MissionService missionService;
    private final MissionQueryServiceImpl missionQueryServiceImpl;

    @PostMapping("/{storeId}/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @PathVariable Long storeId,
            @PathVariable Long missionId,
            @RequestParam Long memberId  // 추후 JWT에서 추출
    ) {
        UserMission userMission = missionService.challengeMission(storeId, missionId, memberId);
        return ApiResponse.success(StoreSuccessCode.STORE_SUCCESS_CODE,
                MissionConverter.toChallengeMissionResultDTO(userMission)
        );
    }


    @Override
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer page
    ) {
        // page - 1 : 클라이언트는 1페이지부터 보내지만, JPA는 0페이지부터 시작하므로 보정
        MissionResponseDTO.MissionListDTO result = missionQueryServiceImpl.getMissions(storeId, page - 1);

        return ApiResponse.success(StoreSuccessCode.STORE_SUCCESS_CODE, result);    }

    @Override // 인터페이스 구현
    @GetMapping("/members/{memberId}/missions/challenging")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMyMissions(
            @PathVariable Long memberId,
             @RequestParam Integer page // 검증 로직은 구현체에 붙이는 게 명확합니다.
    ) {
        // 서비스 호출 (페이지 번호 -1 보정)
        MissionResponseDTO.MissionListDTO result = missionQueryServiceImpl.getMyMissions(memberId, page - 1);

        return ApiResponse.success(STORE_SUCCESS_CODE, result);
    }



}
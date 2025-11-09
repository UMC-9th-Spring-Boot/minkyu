package com.example.umc_9th.domain.mission.controller;

import com.example.umc_9th.domain.converter.MissionConverter;
import com.example.umc_9th.domain.mission.dto.res.MissionResponseDTO;
import com.example.umc_9th.domain.mission.mapping.UserMission;
import com.example.umc_9th.domain.mission.service.MissionService;
import com.example.umc_9th.grobal.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{storeId}/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @PathVariable Long storeId,
            @PathVariable Long missionId,
            @RequestParam Long memberId  // 추후 JWT에서 추출
    ) {
        UserMission userMission = missionService.challengeMission(storeId, missionId, memberId);
        return ApiResponse.onSuccess(
                MissionConverter.toChallengeMissionResultDTO(userMission)
        );
    }
}
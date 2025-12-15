package com.example.umc_9th.domain.mission.controller;

import com.example.umc_9th.domain.mission.dto.res.MissionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import com.example.umc_9th.grobal.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface MissionControllerDocs {
    @Operation(summary = "미션2. 특정 가게의 미션 목록 조회 ", description = "특정 가게의 미션들을 조회합니다. 페이징을 포함합니다.")

    ApiResponse<MissionResponseDTO.MissionListDTO> getMissions(
            @Parameter(description = "가게 ID", required = true) @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1부터 시작)") @RequestParam Integer page
    );

    @Operation(summary = "미션3. 내가 진행중인 미션 조회 ", description = "진행중인 미션을 조회합니다. 페이징을 포함합니다. ")

    ApiResponse<MissionResponseDTO.MissionListDTO> getMyMissions(
            @Parameter(description = "회원 ID", required = true) @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1부터 시작)") @RequestParam Integer page
    );


}

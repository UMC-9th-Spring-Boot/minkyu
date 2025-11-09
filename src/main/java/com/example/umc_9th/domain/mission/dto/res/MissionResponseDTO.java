package com.example.umc_9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResponseDTO {
    @Builder
    public record ChallengeMissionResultDTO(
            Long userMissionId,
            Long missionId,
            String missionTitle,
            String missionDescription,
            String storeName,
            Integer points,
            Boolean status,  // true=진행중, false=완료
            LocalDateTime createdAt
    ) {}
}

package com.example.umc_9th.domain.converter;

import com.example.umc_9th.domain.mission.dto.res.MissionResponseDTO;
import com.example.umc_9th.domain.mission.entity.Mission;
import com.example.umc_9th.domain.mission.mapping.UserMission;

public class MissionConverter {

    public static MissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(
            UserMission userMission
    ) {
        Mission mission = userMission.getMission();

        return MissionResponseDTO.ChallengeMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(mission.getId())
                .missionTitle(mission.getTitle())
                .missionDescription(mission.getDescription())
                .storeName(mission.getStore().getName())
                .points(mission.getPoints())
                .status(userMission.getStatus())
                .build();
    }
}
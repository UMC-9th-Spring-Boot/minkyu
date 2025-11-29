package com.example.umc_9th.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    public record ChallengeMissionResultDTO(
            Long userMissionId,
            Long missionId,
            String missionTitle,
            String missionDescription,
            String storeName,
            Integer points,
            Boolean status,
            LocalDateTime createdAt
    ) {}


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDTO {
        private Long missionId;
        private String title;
        private String description;
        private Integer points;
        private String verificationCode;
        private LocalDate createdAt;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO {
        private List<MissionDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionDTO {
        private Long userMissionId;
        private String storeName;
        private String missionTitle;
        private Integer point;
        private LocalDateTime createdAt;
    }


}
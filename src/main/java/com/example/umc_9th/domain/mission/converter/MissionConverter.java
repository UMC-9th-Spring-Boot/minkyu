package com.example.umc_9th.domain.mission.converter;

import com.example.umc_9th.domain.mission.dto.res.MissionResponseDTO;
import com.example.umc_9th.domain.mission.entity.Mission;
import com.example.umc_9th.domain.mission.mapping.UserMission;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Builder

public class MissionConverter {

    // Entity -> DTO 변환
    public static MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())               // 제목 매핑
                .description(mission.getDescription())   // 설명 매핑
                .points(mission.getPoints())             // 포인트 매핑
                .verificationCode(mission.getVerificationCode()) // 인증코드 매핑
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }

    // Page -> ListDTO 변환
    public static MissionResponseDTO.MissionListDTO toMissionListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionDTO> missionDTOList = missionPage.stream()
                .map(MissionConverter::toMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListDTO.builder()
                .missionList(missionDTOList)
                .listSize(missionDTOList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    // UserMission(매핑 엔티티) -> ChallengeMissionResultDTO(응답 DTO) 변환
    public static MissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(UserMission userMission) {

        return MissionResponseDTO.ChallengeMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .missionTitle(userMission.getMission().getTitle())
                .missionDescription(userMission.getMission().getDescription())
                .storeName(userMission.getMission().getStore().getName())
                .points(userMission.getMission().getPoints())
                .status(true)
                .createdAt(userMission.getCompletedAt())
                .build();
    }



    public static MissionResponseDTO.MyMissionDTO toMyMissionDTO(UserMission userMission) {
        return MissionResponseDTO.MyMissionDTO.builder()
                .userMissionId(userMission.getId())
                .storeName(userMission.getMission().getStore().getName())
                .missionTitle(userMission.getMission().getTitle())
                .point(userMission.getMission().getPoints())
                .createdAt(userMission.getCompletedAt())
                .build();
    }


    public static MissionResponseDTO.MissionListDTO toMyMissionListDTO(Page<UserMission> page) {
        List<MissionResponseDTO.MyMissionDTO> myMissionDTOList = page.stream()
                .map(MissionConverter::toMyMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListDTO.builder()
                // 여기서는 제네릭을 쓰거나 MyMissionDTO 리스트 필드를 DTO에 추가해야 하지만,
                // 편의상 기존 구조에 맞춘다면 아래와 같이 변환하거나 새로 DTO를 파는게 좋습니다.
                // (지금은 MissionListDTO가 MissionDTO 리스트만 받게 되어 있어, MyMissionListDTO를 따로 만드는 것을 추천합니다.)
                // 일단 로직 흐름만 보여드립니다:
                .listSize(myMissionDTOList.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }



}

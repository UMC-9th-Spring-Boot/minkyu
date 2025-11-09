package com.example.umc_9th.domain.mission.execption.code;

import com.example.umc_9th.grobal.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 조회했습니다."),
    ;



    private final HttpStatus status;
    private final String code;
    private final String message;
}
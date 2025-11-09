package com.example.umc_9th.domain.mission.execption.code;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum  MissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 도전 중인 미션입니다."),
    STORE_MISMATCH(HttpStatus.BAD_REQUEST, "MISSION400_2", "해당 가게의 미션이 아닙니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

}

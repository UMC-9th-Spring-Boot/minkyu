package com.example.umc_9th.domain.food.exception.code;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_1",
                    "선호하는 음식을 찾을 수 없습니다."),
            ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

package com.example.umc_9th.domain.food.exception;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.grobal.apiPayload.exception.GeneralException;


public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
package com.example.umc_9th.domain.mission.execption;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.grobal.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
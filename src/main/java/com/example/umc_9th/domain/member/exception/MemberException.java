package com.example.umc_9th.domain.member.exception;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.grobal.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
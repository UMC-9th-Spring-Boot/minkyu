package com.example.umc_9th.domain.test.exception;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.grobal.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}
package com.example.umc_9th.domain.review.exception;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.grobal.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}

package com.example.umc_9th.domain.store.exception;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.grobal.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}

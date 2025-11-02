package com.example.umc_9th.domain.review.exception.code;

import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    // 리뷰 검색 관련 에러
    // 회원 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 ID의 회원을 찾을 수 없습니다."),
    // 평점 유효성
    INVALID_RATING_VALUE(HttpStatus.BAD_REQUEST, "REVIEW400_1", "평점 값은 1에서 5 사이여야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
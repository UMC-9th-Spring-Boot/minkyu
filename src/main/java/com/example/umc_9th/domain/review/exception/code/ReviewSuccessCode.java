package com.example.umc_9th.domain.review.exception.code;
import com.example.umc_9th.grobal.apiPayload.code.BaseSuccessCode;
import com.example.umc_9th.grobal.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_REGISTER_SUCCESS(HttpStatus.OK, "REVIEW200", "리뷰 등록에 성공했습니다."),
    REVIEW_UPDATE_SUCCESS(HttpStatus.OK, "REVIEW200", "리뷰 수정에 성공했습니다."),
    REVIEW_DELETE_SUCCESS(HttpStatus.OK, "REVIEW200", "리뷰 삭제에 성공했습니다."),
    FOUND(HttpStatus.OK, "REVIEW200", "리뷰 조회에 성공했습니다.");

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

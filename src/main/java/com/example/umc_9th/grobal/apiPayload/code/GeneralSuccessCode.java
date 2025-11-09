package com.example.umc_9th.grobal.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{


    // 성공응답
    OK(HttpStatus.OK,"Common200","응답 성공"),
    CREATED(HttpStatus.CREATED, "COMMON201", "요청 성공,리소스 생성됨"),
    MEMBER_FOUND(HttpStatus.OK, "MEMBER200", "회원을 조회했습니다."),
    REVIEWS_FOUND(HttpStatus.OK, "REVIEW200", "리뷰 목록을 성공적으로 조회했습니다.");





    private  HttpStatus status;
    private  String code;
    private  String message;
    // BaseSuccessCode 인터페이스 구현
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

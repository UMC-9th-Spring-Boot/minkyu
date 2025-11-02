package com.example.umc_9th.domain.test.dto.res;


import lombok.Builder;
import lombok.Getter;

//DTO 하나만 만드록 안에다 스태틱으로 여러개
//RequestDTO는  보통 프론트 그대로 받기 떄문에 빌더 패턴 적용할 필요 없음
public class TestResDTO {
    //DTO 자체는 수많은 곳에서 사용이 될 수 있기에  static class로 만들면 매번 class 파일을 만들 필요 없음
    @Builder
    @Getter
    public static class Testing {
        private String testing;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}

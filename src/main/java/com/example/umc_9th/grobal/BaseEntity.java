package com.example.umc_9th.grobal;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt; // 생성 일시

    @LastModifiedDate
    private LocalDateTime updatedAt; // 수정 일시

    // deletedAt은 비즈니스 로직에 따라 직접 처리해야 하므로
    // Auditing 기능 대신 일반 필드로 남겨둡니다.
    private LocalDateTime deletedAt; // 삭제 일시
}
package com.example.umc_9th.domain.review.repository;

import com.example.umc_9th.domain.review.entity.QReview;
import com.example.umc_9th.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {


    // global에서 수동으로 Bean 등록
    private final JPAQueryFactory queryFactory;
    // Q 클래스 만들기
    private final QReview review= QReview.review;


    @Override
    public List<Review> findMyReviews(Long memberId, Long storeId, Integer rating ) {

        return queryFactory
                .selectFrom(review)
                // Member 와 review 패치 조인
                .join(review.member).fetchJoin()
                .join(review.store).fetchJoin()
                .where(
                        // 내가 쓴 리뷰 찾기
                        review.member.memberId.eq(memberId),
                        //  가게별 필터
                        //.store.id.eq(storeId),
                        storeIdEq(storeId),
                        // 별점별 필터
                        ratingEq(rating)
//                        review.rating.eq(rating)
                )
                .fetch();
    }

    // 가게 id 있는지 확인 (리팩토링 메서드)
    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? review.store.id.eq(storeId) : null;
    }
    private BooleanExpression ratingEq(Integer rating) {
        return rating != null ? review.rating.eq(rating) : null;
    }


}

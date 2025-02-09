package com.api.resume.adapter.persistence.resumereview;

import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.entity.QResumeReview;
import com.api.resume.domain.entity.ResumeReview;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeReviewAdapter {

    private final ResumeReviewJpaRepository resumeReviewJpaRepository;
    private final JPAQueryFactory queryFactory;

    public List<ResumeReview> getAllResumeReview(final ResumeReviewListQuery query, final String direction) {
        QResumeReview resumeReview = QResumeReview.resumeReview;
        return queryFactory
                .selectFrom(resumeReview)
                .where(
                        titleContains(query.getTitle()),
                        companyNameContains(query.getCompanyName()),
                        keywordContains(query.getKeyword())
                )
                .orderBy(getSortOrder(direction))
                .fetch();
    }

    public ResumeReview getResumeReview(final long reviewId) {
        return resumeReviewJpaRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트 회고가 존재하지 않습니다. review Id : " + reviewId));
    }

    public ResumeReview save(final ResumeReview resumeReview) {
        return resumeReviewJpaRepository.save(resumeReview);
    }

    public void delete(final long reviewId) {
        resumeReviewJpaRepository.deleteById(reviewId);
    }


    private BooleanExpression titleContains(final String title) {
        return title != null ? QResumeReview.resumeReview.title.containsIgnoreCase(title) : null;
    }

    private BooleanExpression companyNameContains(final String companyName) {
        return companyName != null ? QResumeReview.resumeReview.companyName.containsIgnoreCase(companyName) : null;
    }

    private BooleanExpression keywordContains(final String keyword) {
        return keyword != null ? QResumeReview.resumeReview.keywords.containsIgnoreCase(keyword) : null;
    }

    private OrderSpecifier<?> getSortOrder(final String sortBy) {
        QResumeReview resumeReview = QResumeReview.resumeReview;

        PathBuilder<?> entityPath = new PathBuilder<>(ResumeReview.class, "resumeReview");
        if(sortBy != null) {
            Order order = "DESC".equalsIgnoreCase(sortBy) ? Order.DESC : Order.ASC;
            return new OrderSpecifier<>(order, entityPath.getDate("projectStartDate", java.time.LocalDateTime.class));
        }
        return resumeReview.projectStartDate.desc();
    }
}

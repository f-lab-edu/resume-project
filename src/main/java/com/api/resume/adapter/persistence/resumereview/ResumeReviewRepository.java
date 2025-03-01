package com.api.resume.adapter.persistence.resumereview;

import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.entity.QResumeReview;
import com.api.resume.domain.entity.ResumeReview;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeReviewRepository {

    private final ResumeReviewJpaRepository resumeReviewJpaRepository;
    private final JPAQueryFactory queryFactory;

    public List<ResumeReview> getAllResumeReview(final ResumeReviewListQuery query, final String direction) {
        QResumeReview resumeReview = QResumeReview.resumeReview;
        return queryFactory
                .selectFrom(resumeReview)
                .where(
                        titleContains(query.title()),
                        companyNameContains(query.companyName()),
                        keywordContains(query.keyword())
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
        return Optional.ofNullable(title)
                .map(e -> QResumeReview.resumeReview.title.contains(title))
                .orElse(null);
    }

    private BooleanExpression companyNameContains(final String companyName) {
        return Optional.ofNullable(companyName)
                .map(e -> QResumeReview.resumeReview.companyName.contains(companyName))
                .orElse(null);
    }

    private BooleanExpression keywordContains(final String keyword) {
        return Optional.ofNullable(keyword)
                .map(e -> QResumeReview.resumeReview.keywords.contains(keyword))
                .orElse(null);
    }

    private OrderSpecifier<?> getSortOrder(final String sortBy) {
        QResumeReview resumeReview = QResumeReview.resumeReview;

        PathBuilder<?> entityPath = new PathBuilder<>(ResumeReview.class, "resumeReview");
        if (sortBy != null) {
            Order order = "DESC".equalsIgnoreCase(sortBy) ? Order.DESC : Order.ASC;
            return new OrderSpecifier<>(order, entityPath.getDate("projectStartDate", java.time.LocalDateTime.class));
        }
        return resumeReview.projectStartDate.desc();
    }
}

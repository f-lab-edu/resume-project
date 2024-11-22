package com.api.resume.adapter.persistence;

import com.api.resume.application.service.query.ResumeReviewListQuery;
import com.api.resume.domain.entity.ResumeReview;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ResumeReviewSpecificationBuilder {
    public static Specification<ResumeReview> buildSearch(ResumeReviewListQuery query) {
        Specification<ResumeReview> spec = Specification.where(null);

        if (query == null) {
            return spec;
        }

        if (Objects.nonNull(query.getTitle())) {
            spec = spec.and(titleContains(query.getTitle()));
        }

        if (Objects.nonNull(query.getCompanyName())) {
            spec = spec.and(companyNameContains(query.getCompanyName()));
        }

        if (Objects.nonNull(query.getKeyword())) {
            spec = spec.and(keywordContains(query.getKeyword()));
        }
        return spec;
    }

    private static Specification<ResumeReview> titleContains(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + title.toLowerCase() + "%"
        );
    }


    private static Specification<ResumeReview> companyNameContains(String companyName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("companyName")),
                "%" + companyName.toLowerCase() + "%"
        );
    }

    private static Specification<ResumeReview> keywordContains(String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("keywords")),
                "%" + keyword.toLowerCase() + "%"
        );
    }

}

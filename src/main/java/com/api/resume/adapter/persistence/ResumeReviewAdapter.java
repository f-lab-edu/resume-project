package com.api.resume.adapter.persistence;

import com.api.resume.application.service.command.ResumeReviewCreateCommand;
import com.api.resume.application.service.command.ResumeReviewUpdateCommand;
import com.api.resume.application.service.query.ResumeReviewListQuery;
import com.api.resume.domain.entity.ResumeReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeReviewAdapter {

    private final ResumeReviewJpaRepository resumeReviewJpaRepository;

    public List<ResumeReview> getAllResumeReview(final ResumeReviewListQuery query, final String direction) {
        Specification<ResumeReview> spec = ResumeReviewSpecificationBuilder.buildSearch(query);

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Sort sort = Sort.by(sortDirection, "projectStartDate");

        return resumeReviewJpaRepository.findAll(spec, sort);
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
}

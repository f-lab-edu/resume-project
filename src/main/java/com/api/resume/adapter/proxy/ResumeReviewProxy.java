package com.api.resume.adapter.proxy;

import com.api.resume.application.service.query.ResumeReviewListQuery;
import com.api.resume.application.usecase.ResumeReviewDetailUseCase;
import com.api.resume.application.usecase.ResumeReviewListUseCase;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeReviewProxy {

    private final ResumeReviewListUseCase resumeReviewListUseCase;
    private final ResumeReviewDetailUseCase resumeReviewDetailUseCase;

    public List<ResumeReviewListDto> getResumeReviewList(final ResumeReviewListQuery query, final String direction) {
        return resumeReviewListUseCase.getAllResumeReviewList(query, direction);
    }

    public ResumeReviewDetailDto getResumeReview(final long reviewId) {
        return resumeReviewDetailUseCase.getResumeReview(reviewId);
    }
}

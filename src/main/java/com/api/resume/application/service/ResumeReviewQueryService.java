package com.api.resume.application.service;

import com.api.resume.adapter.persistence.ResumeReviewAdapter;
import com.api.resume.application.converter.ResumeReviewDtoConverter;
import com.api.resume.application.service.query.ResumeReviewListQuery;
import com.api.resume.application.usecase.ResumeReviewDetailUseCase;
import com.api.resume.application.usecase.ResumeReviewListUseCase;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.api.resume.domain.entity.ResumeReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeReviewQueryService implements ResumeReviewListUseCase, ResumeReviewDetailUseCase {

    private final ResumeReviewAdapter resumeReviewAdapter;

    @Override
    public List<ResumeReviewListDto> getAllResumeReviewList(final ResumeReviewListQuery query, final String direction) {
        List<ResumeReview> resumeReviews = resumeReviewAdapter.getAllResumeReview(query, direction);
        return ResumeReviewDtoConverter.INSTANCE.toResumeReviewListDtos(resumeReviews);
    }

    @Override
    public ResumeReviewDetailDto getResumeReview(long reviewId) {
        ResumeReview resumeReview = resumeReviewAdapter.getResumeReview(reviewId);
        return ResumeReviewDtoConverter.INSTANCE.toResumeReviewDetailDto(resumeReview);
    }
}

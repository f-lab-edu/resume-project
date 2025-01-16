package com.api.resume.application.service.resumereview;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewAdapter;
import com.api.resume.application.service.resumereview.query.ResumeReviewListQuery;
import com.api.resume.application.usecase.resumereview.ResumeReviewDetailUseCase;
import com.api.resume.application.usecase.resumereview.ResumeReviewListUseCase;
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
        return ResumeReviewListDto.from(resumeReviews);
    }

    @Override
    public ResumeReviewDetailDto getResumeReview(long reviewId) {
        ResumeReview resumeReview = resumeReviewAdapter.getResumeReview(reviewId);
        return ResumeReviewDetailDto.from(resumeReview);
    }
}

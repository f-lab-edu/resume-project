package com.api.resume.application.resumereview;

import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.api.resume.domain.entity.ResumeReview;

import java.util.List;

public interface ResumeReviewUseCase {
    List<ResumeReviewListDto> getAllResumeReviewList(final ResumeReviewListQuery query, final String direction);
    ResumeReviewDetailDto getResumeReview(final long reviewId);
    long create(final ResumeReviewCreateCommand command);
    long update(final ResumeReviewUpdateCommand command);
    long delete(final long reviewId);
}

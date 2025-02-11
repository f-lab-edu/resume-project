package com.api.resume.application.resumereview;

import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;

import java.util.List;

public interface ResumeReviewUseCase {
    List<ResumeReviewListDto> getAllResumeReviewList(final ResumeReviewListQuery query, final String direction);
    ResumeReviewDetailDto getResumeReview(final long reviewId);
    void create(final ResumeReviewCreateCommand command);
    void update(final ResumeReviewUpdateCommand command);
    void delete(final long reviewId);
}

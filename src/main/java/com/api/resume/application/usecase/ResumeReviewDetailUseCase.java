package com.api.resume.application.usecase;

import com.api.resume.domain.dto.ResumeReviewDetailDto;

public interface ResumeReviewDetailUseCase {

    ResumeReviewDetailDto getResumeReview(final long reviewId);
}

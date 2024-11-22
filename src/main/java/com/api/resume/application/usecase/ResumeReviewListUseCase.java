package com.api.resume.application.usecase;

import com.api.resume.application.service.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewListDto;

import java.util.List;

public interface ResumeReviewListUseCase {
    List<ResumeReviewListDto> getAllResumeReviewList(final ResumeReviewListQuery query, final String direction);
}

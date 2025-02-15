package com.api.resume.adapter.payload.resumereivew;

import com.api.resume.domain.dto.ResumeReviewListDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ResumeReviewListResponse(Long reviewId,
                                       String companyName,
                                       String title,
                                       LocalDate projectStartDate,
                                       LocalDate projectEndDate,
                                       String keywords) {

    public static List<ResumeReviewListResponse> from(final List<ResumeReviewListDto> resumeReviews) {
        return resumeReviews.stream()
                .map(ResumeReviewListResponse::fromDto)
                .collect(Collectors.toList());
    }

    private static ResumeReviewListResponse fromDto(final ResumeReviewListDto dto) {
        return new ResumeReviewListResponse(dto.reviewId()
                , dto.companyName()
                , dto.title()
                , dto.projectStartDate()
                , dto.projectEndDate()
                , dto.keywords());
    }
}

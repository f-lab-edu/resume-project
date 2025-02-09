package com.api.resume.domain.dto;

import com.api.resume.domain.entity.ResumeReview;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
public class ResumeReviewListDto {
    Long reviewId;
    String companyName;
    String title;
    LocalDate projectStartDate;
    LocalDate projectEndDate;
    String keywords;

    public static List<ResumeReviewListDto> from(final List<ResumeReview> resumeReviews) {
        return resumeReviews.stream()
                .map(ResumeReviewListDto::fromResumeReview)
                .collect(Collectors.toList());
    }

    private static ResumeReviewListDto fromResumeReview(final ResumeReview resumeReview) {
        return new ResumeReviewListDto(resumeReview.getId()
                , resumeReview.getCompanyName()
                , resumeReview.getTitle()
                , resumeReview.getProjectStartDate()
                , resumeReview.getProjectEndDate()
                , resumeReview.getKeywords());
    }
}

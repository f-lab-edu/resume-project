package com.api.resume.domain.dto;

import com.api.resume.domain.entity.ResumeReview;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ResumeReviewDetailDto {
    Long reviewId;
    String title;
    String companyName;
    String situation;
    String task;
    String actionsTaken;
    String result;
    String keywords;
    LocalDate projectStartDate;
    LocalDate projectEndDate;

    public static ResumeReviewDetailDto from(final ResumeReview resumeReview) {
        return new ResumeReviewDetailDto(resumeReview.getId()
                , resumeReview.getTitle()
                , resumeReview.getCompanyName()
                , resumeReview.getSituation()
                , resumeReview.getTask()
                , resumeReview.getActionsTaken()
                , resumeReview.getResult()
                , resumeReview.getKeywords()
                , resumeReview.getProjectStartDate()
                , resumeReview.getProjectEndDate()
        );
    }

}

package com.api.resume.adapter.payload.resumereivew;

import com.api.resume.domain.dto.ResumeReviewDetailDto;

import java.time.LocalDate;

public record ResumeReviewDetailResponse(Long reviewId,
                                         String title,
                                         String companyName,
                                         LocalDate projectStartDate,
                                         LocalDate projectEndDate,
                                         String keywords,
                                         String situation,
                                         String task,
                                         String actionsTaken,
                                         String result) {

    public static ResumeReviewDetailResponse from(final ResumeReviewDetailDto dto) {
        return new ResumeReviewDetailResponse(dto.reviewId()
                , dto.title()
                , dto.companyName()
                , dto.projectStartDate()
                , dto.projectEndDate()
                , dto.keywords()
                , dto.situation()
                , dto.task()
                , dto.actionsTaken()
                , dto.result());
    }
}

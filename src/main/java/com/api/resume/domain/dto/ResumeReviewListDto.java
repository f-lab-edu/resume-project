package com.api.resume.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ResumeReviewListDto {
    Long reviewId;
    String companyName;
    String title;
    LocalDate projectStartDate;
    LocalDate projectEndDate;
    String keywords;
}

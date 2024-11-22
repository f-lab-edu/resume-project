package com.api.resume.domain.dto;

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
}

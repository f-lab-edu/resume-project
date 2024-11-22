package com.api.resume.adapter.payload.resumereivew;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ResumeReviewListResponse {
    Long reviewId;
    String companyName;
    String title;
    LocalDate projectStartDate;
    LocalDate projectEndDate;
    String keywords;
}

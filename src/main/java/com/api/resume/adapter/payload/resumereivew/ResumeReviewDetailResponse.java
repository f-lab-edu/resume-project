package com.api.resume.adapter.payload.resumereivew;

import lombok.Value;
import java.time.LocalDate;

@Value
public class ResumeReviewDetailResponse {
    Long reviewId;
    String title;
    String companyName;
    LocalDate projectStartDate;
    LocalDate projectEndDate;
    String keywords;
    String situation;
    String task;
    String actionsTaken;
    String result;
}

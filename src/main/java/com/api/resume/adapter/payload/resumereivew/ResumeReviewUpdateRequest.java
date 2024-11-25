package com.api.resume.adapter.payload.resumereivew;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ResumeReviewUpdateRequest {
    Long userId;
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

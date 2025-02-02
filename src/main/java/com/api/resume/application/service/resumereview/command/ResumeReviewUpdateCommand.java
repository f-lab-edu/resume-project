package com.api.resume.application.service.resumereview.command;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ResumeReviewUpdateCommand {
    Long userId;
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

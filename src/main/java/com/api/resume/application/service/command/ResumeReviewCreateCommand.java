package com.api.resume.application.service.command;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ResumeReviewCreateCommand {
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

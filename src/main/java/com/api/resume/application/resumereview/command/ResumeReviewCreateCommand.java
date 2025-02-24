package com.api.resume.application.resumereview.command;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
public record ResumeReviewCreateCommand(Long userId, String title, String companyName, LocalDate projectStartDate,
                                        LocalDate projectEndDate, String keywords, String situation, String task,
                                        String actionsTaken, String result) {
}

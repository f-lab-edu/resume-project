package com.api.resume.adapter.payload.resumereivew;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ResumeReviewUpdateRequest(Long userId,
                                        String title,
                                        String companyName,
                                        LocalDate projectStartDate,
                                        LocalDate projectEndDate,
                                        String keywords,
                                        String situation,
                                        String task,
                                        String actionsTaken,
                                        String result) {
}

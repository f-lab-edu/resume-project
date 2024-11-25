package com.api.resume.application.service.query;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResumeReviewListQuery {
    String companyName;
    String title;
    String keyword;
}

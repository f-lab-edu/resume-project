package com.api.resume.application.resumereview.query;

import lombok.Builder;
import lombok.Value;

@Builder
public record ResumeReviewListQuery(String companyName, String title, String keyword) {
}

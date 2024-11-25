package com.api.resume.adapter.payload.resumereivew;

import lombok.Value;

@Value
public class ResumeReviewListRequest {
    String companyName;
    String title;
    String keyword;
}

package com.api.resume.adapter.payload.resumeintroduction;

import lombok.Value;

@Value
public class ResumeIntroductionUpdateRequest {
    long resumeIntroductionId;
    String title;
    String content;
}

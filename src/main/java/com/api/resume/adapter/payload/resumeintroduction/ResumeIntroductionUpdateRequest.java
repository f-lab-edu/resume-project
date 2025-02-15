package com.api.resume.adapter.payload.resumeintroduction;

public record ResumeIntroductionUpdateRequest(long resumeIntroductionId,
                                              String title,
                                              String content) {
}

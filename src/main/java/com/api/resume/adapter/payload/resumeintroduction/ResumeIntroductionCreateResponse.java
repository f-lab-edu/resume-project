package com.api.resume.adapter.payload.resumeintroduction;

public record ResumeIntroductionCreateResponse(long resumeIntroductionId) {

    public static ResumeIntroductionCreateResponse from(final long resumeIntroductionId) {
        return new ResumeIntroductionCreateResponse(resumeIntroductionId);
    }

}

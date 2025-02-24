package com.api.resume.adapter.payload.resumeintroduction;

public record ResumeIntroductionUpdateResponse(long resumeIntroductionId) {

    public static ResumeIntroductionUpdateResponse from(long resumeIntroductionId) {
        return new ResumeIntroductionUpdateResponse(resumeIntroductionId);
    }

}

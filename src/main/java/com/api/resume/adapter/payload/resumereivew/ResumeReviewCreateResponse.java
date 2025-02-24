package com.api.resume.adapter.payload.resumereivew;

public record ResumeReviewCreateResponse(long resumeReviewId) {

    public static ResumeReviewCreateResponse from(final long resumeReviewId) {
        return new ResumeReviewCreateResponse(resumeReviewId);
    }

}

package com.api.resume.adapter.payload.resumereivew;

public record ResumeReviewUpdateResponse(long resumeReviewId) {

    public static ResumeReviewUpdateResponse from(final long resumeReviewId) {
        return new ResumeReviewUpdateResponse(resumeReviewId);
    }

}

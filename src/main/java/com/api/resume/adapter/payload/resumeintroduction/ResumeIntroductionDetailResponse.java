package com.api.resume.adapter.payload.resumeintroduction;

import com.api.resume.domain.dto.ResumeIntroductionDetailDto;

import java.time.LocalDateTime;

public record ResumeIntroductionDetailResponse(long resumeIntroductionId,
                                               String title,
                                               String content,
                                               LocalDateTime createdAt,
                                               LocalDateTime updatedAt) {

    public static ResumeIntroductionDetailResponse from(final ResumeIntroductionDetailDto result) {
        return new ResumeIntroductionDetailResponse(
                result.resumeIntroductionId(),
                result.title(),
                result.content(),
                result.createdAt(),
                result.updatedAt()
        );
    }
}

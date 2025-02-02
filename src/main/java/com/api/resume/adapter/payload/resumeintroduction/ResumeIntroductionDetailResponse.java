package com.api.resume.adapter.payload.resumeintroduction;

import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ResumeIntroductionDetailResponse {

    long resumeIntroductionId;
    String title;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public static ResumeIntroductionDetailResponse from(ResumeIntroductionDetailDto result) {
        return new ResumeIntroductionDetailResponse(
                result.getResumeIntroductionId(),
                result.getTitle(),
                result.getContent(),
                result.getCreatedAt(),
                result.getUpdatedAt()
        );
    }
}

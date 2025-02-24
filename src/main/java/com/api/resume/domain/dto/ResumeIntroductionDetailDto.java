package com.api.resume.domain.dto;

import com.api.resume.domain.entity.ResumeIntroduction;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
public record ResumeIntroductionDetailDto(long resumeIntroductionId,
                                          String title,
                                          String content,
                                          LocalDateTime createdAt,
                                          LocalDateTime updatedAt) {

    public static ResumeIntroductionDetailDto from(final ResumeIntroduction resumeIntroduction) {
        return new ResumeIntroductionDetailDto(
                resumeIntroduction.getResumeIntroductionId(),
                resumeIntroduction.getTitle(),
                resumeIntroduction.getContent(),
                resumeIntroduction.getCreatedAt(),
                resumeIntroduction.getUpdatedAt()
        );
    }
}

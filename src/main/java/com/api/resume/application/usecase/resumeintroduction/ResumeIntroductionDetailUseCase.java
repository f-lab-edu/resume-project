package com.api.resume.application.usecase.resumeintroduction;

import com.api.resume.domain.dto.ResumeIntroductionDetailDto;

public interface ResumeIntroductionDetailUseCase {
    ResumeIntroductionDetailDto getResumeIntroduction(final long resumeIntroductionId);
}

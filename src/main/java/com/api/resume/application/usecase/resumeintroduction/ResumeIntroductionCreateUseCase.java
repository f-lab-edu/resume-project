package com.api.resume.application.usecase.resumeintroduction;

import com.api.resume.application.service.resumeintroduction.command.ResumeIntroductionCreateCommand;

public interface ResumeIntroductionCreateUseCase {
    void create(ResumeIntroductionCreateCommand command);
}

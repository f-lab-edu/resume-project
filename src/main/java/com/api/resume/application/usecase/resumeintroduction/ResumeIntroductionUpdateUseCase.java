package com.api.resume.application.usecase.resumeintroduction;

import com.api.resume.application.service.resumeintroduction.command.ResumeIntroductionUpdateCommand;

public interface ResumeIntroductionUpdateUseCase {
    void update(ResumeIntroductionUpdateCommand command);
}

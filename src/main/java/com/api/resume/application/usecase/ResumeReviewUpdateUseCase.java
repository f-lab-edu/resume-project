package com.api.resume.application.usecase;

import com.api.resume.application.service.command.ResumeReviewUpdateCommand;

public interface ResumeReviewUpdateUseCase {
    void update(final ResumeReviewUpdateCommand command);
}

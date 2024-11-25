package com.api.resume.application.usecase;

import com.api.resume.application.service.command.ResumeReviewCreateCommand;

public interface ResumeReviewCreateUseCase {
    void create(final ResumeReviewCreateCommand command);
}

package com.api.resume.application.usecase.resumereview;

import com.api.resume.application.service.resumereview.command.ResumeReviewCreateCommand;

public interface ResumeReviewCreateUseCase {
    void create(final ResumeReviewCreateCommand command);
}

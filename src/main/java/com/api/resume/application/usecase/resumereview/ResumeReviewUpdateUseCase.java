package com.api.resume.application.usecase.resumereview;

import com.api.resume.application.service.resumereview.command.ResumeReviewUpdateCommand;

public interface ResumeReviewUpdateUseCase {
    void update(final ResumeReviewUpdateCommand command);
}

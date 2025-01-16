package com.api.resume.application.service.resumereview;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewAdapter;
import com.api.resume.application.service.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.service.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.usecase.resumereview.ResumeReviewCreateUseCase;
import com.api.resume.application.usecase.resumereview.ResumeReviewDeleteUseCase;
import com.api.resume.application.usecase.resumereview.ResumeReviewUpdateUseCase;
import com.api.resume.domain.entity.ResumeReview;
import com.api.resume.domain.service.ResumeReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ResumeReviewCommandService implements ResumeReviewCreateUseCase
                                                , ResumeReviewUpdateUseCase
                                                , ResumeReviewDeleteUseCase {

    private final ResumeReviewAdapter resumeReviewAdapter;
    private final ResumeReviewService resumeReviewService;

    @Override
    public void create(final ResumeReviewCreateCommand command) {
        resumeReviewService.validateUser(command.getUserId());
        resumeReviewService.validDate(command.getProjectStartDate(), command.getProjectEndDate());

        ResumeReview resumeReview = ResumeReview.create(command);
        resumeReviewAdapter.save(resumeReview);
    }

    @Override
    public void update(final ResumeReviewUpdateCommand command) {
        resumeReviewService.validDate(command.getProjectStartDate(), command.getProjectEndDate());

        final ResumeReview resumeReview = resumeReviewAdapter.getResumeReview(command.getReviewId());
        resumeReview.modify(command);
    }

    @Override
    public void delete(long reviewId) {
        resumeReviewAdapter.delete(reviewId);
    }
}

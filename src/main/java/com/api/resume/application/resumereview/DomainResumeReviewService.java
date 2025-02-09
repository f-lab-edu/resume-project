package com.api.resume.application.resumereview;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewAdapter;
import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.api.resume.domain.entity.ResumeReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainResumeReviewService implements ResumeReviewService{

    private final ResumeReviewAdapter resumeReviewAdapter;
    private final com.api.resume.domain.service.ResumeReviewService resumeReviewService;

    @Transactional(readOnly = true)
    @Override
    public List<ResumeReviewListDto> getAllResumeReviewList(ResumeReviewListQuery query, String direction) {
        return List.of();
    }

    @Transactional(readOnly = true)
    @Override
    public ResumeReviewDetailDto getResumeReview(long reviewId) {
        return null;
    }

    @Transactional
    @Override
    public void create(final ResumeReviewCreateCommand command) {
        resumeReviewService.validateUser(command.getUserId());
        resumeReviewService.validDate(command.getProjectStartDate(), command.getProjectEndDate());

        ResumeReview resumeReview = ResumeReview.create(command);
        resumeReviewAdapter.save(resumeReview);
    }

    @Transactional
    @Override
    public void update(final ResumeReviewUpdateCommand command) {
        resumeReviewService.validDate(command.getProjectStartDate(), command.getProjectEndDate());

        final ResumeReview resumeReview = resumeReviewAdapter.getResumeReview(command.getReviewId());
        resumeReview.modify(command);
    }

    @Transactional
    @Override
    public void delete(long reviewId) {
        resumeReviewAdapter.delete(reviewId);
    }
}

package com.api.resume.application.resumereview;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewAdapter;
import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.api.resume.domain.entity.ResumeReview;
import com.api.resume.domain.service.ResumeReviewDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeReviewService implements ResumeReviewUseCase {

    private final ResumeReviewAdapter resumeReviewAdapter;
    private final ResumeReviewDomainService resumeReviewDomainService;

    @Transactional(readOnly = true)
    @Override
    public List<ResumeReviewListDto> getAllResumeReviewList(final ResumeReviewListQuery query,
                                                            final String direction) {
        return ResumeReviewListDto.from(resumeReviewAdapter.getAllResumeReview(query, direction));
    }

    @Transactional(readOnly = true)
    @Override
    public ResumeReviewDetailDto getResumeReview(final long reviewId) {
        return ResumeReviewDetailDto.from(resumeReviewAdapter.getResumeReview(reviewId));
    }

    @Transactional
    @Override
    public void create(final ResumeReviewCreateCommand command) {
        resumeReviewDomainService.validateUser(command.getUserId());
        resumeReviewDomainService.validDate(command.getProjectStartDate(), command.getProjectEndDate());

        ResumeReview resumeReview = ResumeReview.create(command);
        resumeReviewAdapter.save(resumeReview);
    }

    @Transactional
    @Override
    public void update(final ResumeReviewUpdateCommand command) {
        resumeReviewDomainService.validDate(command.getProjectStartDate(), command.getProjectEndDate());

        final ResumeReview resumeReview = resumeReviewAdapter.getResumeReview(command.getReviewId());
        resumeReview.modify(command);
    }

    @Transactional
    @Override
    public void delete(final long reviewId) {
        resumeReviewAdapter.delete(reviewId);
    }
}

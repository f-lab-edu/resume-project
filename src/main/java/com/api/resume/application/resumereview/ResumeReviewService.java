package com.api.resume.application.resumereview;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewRepository;
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

    private final ResumeReviewRepository resumeReviewRepository;
    private final ResumeReviewDomainService resumeReviewDomainService;

    @Transactional(readOnly = true)
    @Override
    public List<ResumeReviewListDto> getAllResumeReviewList(final ResumeReviewListQuery query,
                                                            final String direction) {
        return ResumeReviewListDto.from(resumeReviewRepository.getAllResumeReview(query, direction));
    }

    @Transactional(readOnly = true)
    @Override
    public ResumeReviewDetailDto getResumeReview(final long reviewId) {
        return ResumeReviewDetailDto.from(resumeReviewRepository.getResumeReview(reviewId));
    }

    @Transactional
    @Override
    public long create(final ResumeReviewCreateCommand command) {
        resumeReviewDomainService.validateUser(command.userId());
        resumeReviewDomainService.validDate(command.projectStartDate(), command.projectEndDate());

        ResumeReview resumeReview = ResumeReview.create(command);
        return resumeReviewRepository.save(resumeReview).getId();
    }

    @Transactional
    @Override
    public long update(final ResumeReviewUpdateCommand command) {
        resumeReviewDomainService.validDate(command.getProjectStartDate(), command.getProjectEndDate());

        final ResumeReview resumeReview = resumeReviewRepository.getResumeReview(command.getReviewId());
        resumeReview.modify(command);
        return resumeReview.getId();
    }

    @Transactional
    @Override
    public long delete(final long reviewId) {
        resumeReviewRepository.delete(reviewId);
        return reviewId;
    }
}

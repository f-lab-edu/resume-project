package com.api.resume.application.service;

import com.api.resume.adapter.persistence.ResumeReviewAdapter;
import com.api.resume.application.service.command.ResumeReviewCreateCommand;
import com.api.resume.application.service.command.ResumeReviewUpdateCommand;
import com.api.resume.domain.entity.ResumeReview;
import com.api.resume.domain.service.ResumeReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class ResumeReviewCommandServiceTest {

    @Mock
    private ResumeReviewAdapter resumeReviewAdapter;

    @Mock
    private ResumeReviewService resumeReviewService;

    @InjectMocks
    private ResumeReviewCommandService resumeReviewCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 프로젝트_회고_리뷰_등록() {
        ResumeReviewCreateCommand command = ResumeReviewCreateCommand.builder()
                .title("B project")
                .companyName("lalalal company")
                .projectStartDate(LocalDate.now())
                .projectEndDate(LocalDate.now())
                .keywords("java, spring")
                .situation("situation~~~")
                .task("task~~~~~")
                .actionsTaken("actions~~~~~")
                .result("result~~~~~")
                .build();

        ResumeReview resumeReview = ResumeReview.create(command);

        doNothing().when(resumeReviewAdapter).save(resumeReview);

        resumeReviewCommandService.create(command);

        verify(resumeReviewService).validateUser(command.getUserId());
        verify(resumeReviewService).validDate(command.getProjectStartDate(), command.getProjectEndDate());
        verify(resumeReviewAdapter).save(resumeReview);
    }

    @Test
    void 프로젝트_회고_리뷰_수정() {
        ResumeReviewUpdateCommand command = ResumeReviewUpdateCommand
                .builder()
                .userId(1L)
                .reviewId(1L)
                .title("Updated Reivew")
                .companyName("Updated Company")
                .projectStartDate(LocalDate.of(2023, 1, 1))
                .projectEndDate(LocalDate.of(2023, 12, 31))
                .build();

        ResumeReview resumeReview = mock(ResumeReview.class);

        when(resumeReviewAdapter.getResumeReview(command.getReviewId())).thenReturn(resumeReview);

        resumeReviewCommandService.update(command);

        verify(resumeReviewService).validDate(command.getProjectStartDate(), command.getProjectEndDate());
        verify(resumeReviewAdapter).getResumeReview(command.getReviewId());
        verify(resumeReview).modify(command);
    }

    @Test
    void 프로젝트_회고_리뷰_삭제() {
        long reviewId = 1L;

        resumeReviewCommandService.delete(reviewId);

        verify(resumeReviewAdapter).delete(reviewId);
    }
}
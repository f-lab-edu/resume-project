package com.api.resume.application.service.resumereview;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewAdapter;
import com.api.resume.application.resumereview.ResumeReviewService;
import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.api.resume.domain.entity.ResumeReview;
import com.api.resume.domain.service.ResumeReviewDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ResumeReviewUseCaseTest {

    @InjectMocks
    ResumeReviewService domainResumeReviewService;

    @Mock
    private ResumeReviewDomainService resumeReviewDomainService;

    @Mock
    ResumeReviewAdapter resumeReviewAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 이력서_프로젝트_회고_전체조회() {
        ResumeReviewListQuery query = ResumeReviewListQuery.builder().build();

        List<ResumeReview> mockResumeReviews = List.of(
                ResumeReview.builder()
                        .id(1L)
                        .title("Title A")
                        .companyName("Company A")
                        .projectStartDate(LocalDate.of(2023, 1, 1))
                        .projectEndDate(LocalDate.of(2023, 6, 30))
                        .keywords("Java, Spring")
                        .build(),
                ResumeReview.builder()
                        .id(2L)
                        .title("Title B")
                        .companyName("Company B")
                        .projectStartDate(LocalDate.of(2023, 7, 1))
                        .projectEndDate(LocalDate.of(2023, 12, 31))
                        .keywords("Java, Spring-Boot, JPA")
                        .build()
        );

        given(resumeReviewAdapter.getAllResumeReview(query, "ASC")).willReturn(mockResumeReviews);

        List<ResumeReviewListDto> result = domainResumeReviewService.getAllResumeReviewList(query, "ASC");

        assertThat(result).hasSize(2);
        assertThat(result).extracting("title")
                .containsExactly("Title A", "Title B");
        assertThat(result).extracting("companyName")
                .containsExactly("Company A", "Company B");
    }

    @Test
    void 이력서_프로젝트_회고_키워드_검색_불일치_빈배열반환() {
        ResumeReviewListQuery query = ResumeReviewListQuery.builder()
                .title("Nonexistent Title")
                .build();

        given(resumeReviewAdapter.getAllResumeReview(query, "ASC")).willReturn(List.of());

        List<ResumeReviewListDto> result = domainResumeReviewService.getAllResumeReviewList(query, "ASC");

        assertThat(result).isEmpty();
    }

    @Test
    void 이력서_프로젝트_회고_조회() {
        // Given
        long reviewId = 1L;
        ResumeReview mockResumeReview = ResumeReview.builder()
                .id(reviewId)
                .title("Test Title")
                .companyName("Test Company")
                .projectStartDate(LocalDate.of(2023, 1, 1))
                .projectEndDate(LocalDate.of(2023, 12, 31))
                .keywords("Java, Spring")
                .build();

        ResumeReviewDetailDto expectedDto = ResumeReviewDetailDto.builder()
                .reviewId(reviewId)
                .title("Test Title")
                .companyName("Test Company")
                .projectStartDate(LocalDate.of(2023, 1, 1))
                .projectEndDate(LocalDate.of(2023, 12, 31))
                .keywords("Java, Spring")
                .build();

        given(resumeReviewAdapter.getResumeReview(reviewId)).willReturn(mockResumeReview);

        ResumeReviewDetailDto result = domainResumeReviewService.getResumeReview(reviewId);

        assertThat(result.getReviewId()).isEqualTo(expectedDto.getReviewId());
        assertThat(result.getTitle()).isEqualTo(expectedDto.getTitle());
        assertThat(result.getCompanyName()).isEqualTo(expectedDto.getCompanyName());
        assertThat(result.getProjectStartDate()).isEqualTo(expectedDto.getProjectStartDate());
        assertThat(result.getProjectEndDate()).isEqualTo(expectedDto.getProjectEndDate());
        assertThat(result.getKeywords()).isEqualTo(expectedDto.getKeywords());
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

        domainResumeReviewService.create(command);

        verify(resumeReviewDomainService).validateUser(command.getUserId());
        verify(resumeReviewDomainService).validDate(command.getProjectStartDate(), command.getProjectEndDate());
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

        domainResumeReviewService.update(command);

        verify(resumeReviewDomainService).validDate(command.getProjectStartDate(), command.getProjectEndDate());
        verify(resumeReviewAdapter).getResumeReview(command.getReviewId());
        verify(resumeReview).modify(command);
    }

    @Test
    void 프로젝트_회고_리뷰_삭제() {
        long reviewId = 1L;

        domainResumeReviewService.delete(reviewId);

        verify(resumeReviewAdapter).delete(reviewId);
    }
}

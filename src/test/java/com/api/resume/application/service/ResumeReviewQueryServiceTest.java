package com.api.resume.application.service;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewAdapter;
import com.api.resume.application.service.resumereview.query.ResumeReviewListQuery;
import com.api.resume.application.service.resumereview.ResumeReviewQueryService;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.api.resume.domain.entity.ResumeReview;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ResumeReviewQueryServiceTest {

    @Mock
    ResumeReviewAdapter resumeReviewAdapter;

    @InjectMocks
    private ResumeReviewQueryService resumeReviewQueryService;

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

        List<ResumeReviewListDto> result = resumeReviewQueryService.getAllResumeReviewList(query, "ASC");

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

        List<ResumeReviewListDto> result = resumeReviewQueryService.getAllResumeReviewList(query, "ASC");

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

        ResumeReviewDetailDto result = resumeReviewQueryService.getResumeReview(reviewId);

        assertThat(result.getReviewId()).isEqualTo(expectedDto.getReviewId());
        assertThat(result.getTitle()).isEqualTo(expectedDto.getTitle());
        assertThat(result.getCompanyName()).isEqualTo(expectedDto.getCompanyName());
        assertThat(result.getProjectStartDate()).isEqualTo(expectedDto.getProjectStartDate());
        assertThat(result.getProjectEndDate()).isEqualTo(expectedDto.getProjectEndDate());
        assertThat(result.getKeywords()).isEqualTo(expectedDto.getKeywords());
    }

}

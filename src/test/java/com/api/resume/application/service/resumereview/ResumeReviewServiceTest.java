package com.api.resume.application.service.resumereview;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewRepository;
import com.api.resume.application.resumereview.ResumeReviewService;
import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.api.resume.domain.entity.ResumeReview;
import com.api.resume.domain.service.ResumeReviewDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ResumeReviewServiceTest {


    @InjectMocks
    private ResumeReviewService resumeReviewService;

    @Mock
    private ResumeReviewRepository resumeReviewRepository;

    @Mock
    private ResumeReviewDomainService resumeReviewDomainService;

    private ResumeReview sampleResumeReview;

    @BeforeEach
    void setUp() {
        sampleResumeReview = ResumeReview.builder()
                .id(1L)
                .userId(100L)
                .title("Resume Review")
                .projectStartDate(LocalDate.of(2023, 1, 1))
                .projectEndDate(LocalDate.of(2023, 12, 31))
                .build();
    }

    @Test
    @DisplayName("전체 자기소개 리뷰 리스트 조회")
    void getAllResumeReviewList() {
        // Given
        given(resumeReviewRepository.getAllResumeReview(any(ResumeReviewListQuery.class), anyString()))
                .willReturn(List.of(sampleResumeReview));

        ResumeReviewListQuery query = ResumeReviewListQuery.builder()
                .title("Resume Review")
                .build();
        // When
        List<ResumeReviewListDto> result = resumeReviewService.getAllResumeReviewList(query, "DESC");

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).title()).isEqualTo("Resume Review");
    }

    @Test
    @DisplayName("자기소개 리뷰 단건 조회")
    void getResumeReview() {
        // Given
        given(resumeReviewRepository.getResumeReview(1L)).willReturn(sampleResumeReview);

        // When
        ResumeReviewDetailDto result = resumeReviewService.getResumeReview(1L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.title()).isEqualTo("Resume Review");
    }

    @Test
    @DisplayName("자기소개 리뷰 생성")
    void createResumeReview() {
        // Given
        ResumeReviewCreateCommand command = ResumeReviewCreateCommand.builder()
                .userId(100L)
                .title("New Review")
                .projectStartDate(LocalDate.of(2024, 1, 1))
                .projectEndDate(LocalDate.of(2024, 12, 31))
                .build();

        doNothing().when(resumeReviewDomainService).validateUser(command.userId());
        doNothing().when(resumeReviewDomainService).validDate(command.projectStartDate(), command.projectEndDate());

        ResumeReview savedResumeReview = ResumeReview.create(command);
//        savedResumeReview.setId(1L); // ID를 수동으로 설정

        given(resumeReviewRepository.save(any(ResumeReview.class))).willReturn(savedResumeReview);

        // When
        long resumeReviewId = resumeReviewService.create(command);

        // Then
        assertThat(resumeReviewId).isEqualTo(1L);
    }

    @Test
    @DisplayName("자기소개 리뷰 수정")
    void updateResumeReview() {
        // Given
        ResumeReviewUpdateCommand command = ResumeReviewUpdateCommand.builder()
                .reviewId(1L)
                .title("Updated Review")
                .projectStartDate(LocalDate.of(2024, 1, 1))
                .projectEndDate(LocalDate.of(2024, 12, 31))
                .build();

        doNothing().when(resumeReviewDomainService).validDate(command.getProjectStartDate(), command.getProjectEndDate());
        given(resumeReviewRepository.getResumeReview(1L)).willReturn(sampleResumeReview);

        // When
        long updatedId = resumeReviewService.update(command);

        // Then
        assertThat(updatedId).isEqualTo(1L);
        assertThat(sampleResumeReview.getTitle()).isEqualTo("Updated Review");
    }

    @Test
    @DisplayName("자기소개 리뷰 삭제")
    void deleteResumeReview() {
        // Given
        willDoNothing().given(resumeReviewRepository).delete(1L);

        // When
        long deletedId = resumeReviewService.delete(1L);

        // Then
        assertThat(deletedId).isEqualTo(1L);
        verify(resumeReviewRepository, times(1)).delete(1L);
    }

    @Test
    @DisplayName("잘못된 날짜 입력 시 예외 발생")
    void createResumeReview_InvalidDate() {
        // Given
        ResumeReviewCreateCommand command = ResumeReviewCreateCommand.builder()
                .userId(100L)
                .title("Invalid Review")
                .projectStartDate(LocalDate.of(2025, 1, 1))
                .projectEndDate(LocalDate.of(2024, 12, 31)) // 종료일이 시작일보다 빠름
                .build();

        doThrow(new IllegalArgumentException("Invalid project dates"))
                .when(resumeReviewDomainService)
                .validDate(command.projectStartDate(), command.projectEndDate());

        // When & Then
        assertThatThrownBy(() -> resumeReviewService.create(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid project dates");
    }
}

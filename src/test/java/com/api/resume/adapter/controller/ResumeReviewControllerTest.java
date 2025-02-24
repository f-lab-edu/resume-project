package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.resumereivew.ResumeReviewCreateRequest;
import com.api.resume.adapter.payload.resumereivew.ResumeReviewCreateResponse;
import com.api.resume.adapter.payload.resumereivew.ResumeReviewUpdateRequest;
import com.api.resume.adapter.payload.resumereivew.ResumeReviewUpdateResponse;
import com.api.resume.application.resumereview.ResumeReviewUseCase;
import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ResumeReviewController.class)
class ResumeReviewControllerTest {

    private static final String URI = "/api/v1/resumes/reviews";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ResumeReviewUseCase resumeReviewUseCase;

    @Test
    void 프로젝트_회고_목록_조회() throws Exception {
        ResumeReviewListDto firstResumeReview = ResumeReviewListDto.builder()
                .reviewId(1L)
                .title("test title")
                .companyName("h company")
                .projectStartDate(LocalDate.now().minusMonths(3))
                .projectEndDate(LocalDate.now().minusMonths(1))
                .keywords("java, spring-boot, mysql, 개선작업")
                .build();
        List<ResumeReviewListDto> mockResult = List.of(firstResumeReview);

        when(resumeReviewUseCase.getAllResumeReviewList(any(ResumeReviewListQuery.class), eq("DESC")))
                .thenReturn(mockResult);

        mockMvc.perform(get(URI)
                        .param("direction", "DESC")
                        .param("title", "Test Title")
                        .param("companyName", "h company")
                        .param("keyword", "java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName").value("h company"))
                .andExpect(jsonPath("$[0].title").value("test title"));
    }

    @Test
    void 프로젝트_회고_목록_조건_검색_없는경우_빈목록이_반환된다() throws Exception {
        long reviewId = 1L;
        ResumeReviewDetailDto mockDetailDto = ResumeReviewDetailDto.builder()
                .reviewId(reviewId)
                .title("Test Title")
                .companyName("Test Company")
                .projectStartDate(LocalDate.of(2023, 1, 1))
                .projectEndDate(LocalDate.of(2023, 12, 31))
                .keywords("java, spring-boot, mysql, 개선작업")
                .build();

        when(resumeReviewUseCase.getResumeReview(reviewId)).thenReturn(mockDetailDto);

        mockMvc.perform(get(URI + "/{reviewId}", reviewId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewId").value(reviewId))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.companyName").value("Test Company"))
                .andExpect(jsonPath("$.projectStartDate").value("2023-01-01"))
                .andExpect(jsonPath("$.projectEndDate").value("2023-12-31"))
                .andExpect(jsonPath("$.keywords").value("java, spring-boot, mysql, 개선작업"));
    }

    @Test
    void 리뷰를_조회하면_정상적으로_리뷰_정보가_반환된다() throws Exception {
        Long reviewId = 1L;
        ResumeReviewDetailDto detailDto =
                ResumeReviewDetailDto.builder()
                        .reviewId(reviewId)
                        .title("Test Title")
                        .companyName("Test Company")
                        .projectStartDate(LocalDate.of(2023, 1, 1))
                        .projectEndDate(LocalDate.of(2023, 12, 31))
                        .companyName("Test Company")
                        .situation("Test Situation")
                        .build();

        when(resumeReviewUseCase.getResumeReview(reviewId)).thenReturn(detailDto);

        mockMvc.perform(get(URI + "/{reviewId}", reviewId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.companyName").value("Test Company"))
                .andExpect(jsonPath("$.situation").value("Test Situation"));
    }

    @Test
    void 리뷰를_생성하면_생성된_리뷰의_위치가_반환된다() throws Exception {

        // Given
        long resumeIntroductionId = 1L;
        ResumeReviewCreateRequest request =
                ResumeReviewCreateRequest.builder()
                        .title("New Reivew")
                        .companyName("New Company")
                        .projectStartDate(LocalDate.of(2023, 1, 1))
                        .projectEndDate(LocalDate.of(2023, 12, 31))
                        .build();

        ResumeReviewCreateResponse response = ResumeReviewCreateResponse.from(resumeIntroductionId);
        given(resumeReviewUseCase.create(any(ResumeReviewCreateCommand.class))).willReturn(response.resumeReviewId());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.resumeReviewId").value(1L));
    }

    @Test
    void 리뷰를_수정하면_정상적으로_수정된다() throws Exception {
        long reviewId = 1L;
        ResumeReviewUpdateRequest request =
                ResumeReviewUpdateRequest.builder()
                        .userId(1L)
                        .title("Updated Reivew")
                        .companyName("Updated Company")
                        .projectStartDate(LocalDate.of(2023, 1, 1))
                        .projectEndDate(LocalDate.of(2023, 12, 31))
                        .build();

        // Given
        ResumeReviewUpdateResponse response = ResumeReviewUpdateResponse.from(reviewId);

        given(resumeReviewUseCase.update(any(ResumeReviewUpdateCommand.class))).willReturn(response.resumeReviewId());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put(URI + "/{reviewId}", reviewId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resumeReviewId")
                        .value(1L));

    }

    @Test
    void 리뷰를_삭제하면_정상적으로_삭제된다() throws Exception {
        long reviewId = 1L;

        // Given
        given(resumeReviewUseCase.delete(reviewId)).willReturn(reviewId);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/{reviewId}", reviewId))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
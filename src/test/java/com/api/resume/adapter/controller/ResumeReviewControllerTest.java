package com.api.resume.adapter.controller;

import com.api.resume.adapter.proxy.ResumeReviewProxy;
import com.api.resume.application.service.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResumeReviewController.class)
class ResumeReviewControllerTest {

    private static final String URI = "/api/v1/resumes/reviews";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResumeReviewProxy resumeReviewProxy;

    @Test
    void getAllResumeReview_returnsResumeReviewList() throws Exception {
        ResumeReviewListDto firstResumeReview = ResumeReviewListDto.builder()
                .reviewId(1L)
                .title("test title")
                .companyName("h company")
                .projectStartDate(LocalDate.now().minusMonths(3))
                .projectEndDate(LocalDate.now().minusMonths(1))
                .keywords("java, spring-boot, mysql, 개선작업")
                .build();
        List<ResumeReviewListDto> mockResult = List.of(firstResumeReview);

        Mockito.when(resumeReviewProxy.getResumeReviewList(
                Mockito.any(ResumeReviewListQuery.class),
                Mockito.eq("DESC")
        )).thenReturn(mockResult);

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
    void shouldReturnResumeReviewDetail_whenReviewExists() throws Exception {
        long reviewId = 1L;
        ResumeReviewDetailDto mockDetailDto = ResumeReviewDetailDto.builder()
                .reviewId(reviewId)
                .title("Test Title")
                .companyName("Test Company")
                .projectStartDate(LocalDate.of(2023, 1, 1))
                .projectEndDate(LocalDate.of(2023, 12, 31))
                .keywords("java, spring-boot, mysql, 개선작업")
                .build();

        Mockito.when(resumeReviewProxy.getResumeReview(reviewId)).thenReturn(mockDetailDto);

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
//
//    @Test
//    void shouldReturnNotFound_whenReviewDoesNotExist() throws Exception {
//        long reviewId = 999L;
//
//        Mockito.when(resumeReviewProxy.getResumeReview(reviewId))
//                .thenThrow(new IllegalArgumentException("프로젝트 회고가 존재하지 않습니다. review Id : " + reviewId));
//
//        mockMvc.perform(get(URI + "/{reviewId}", reviewId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound()) // 404 상태 코드 확인
//                .andExpect(jsonPath("$.message").value("프로젝트 회고가 존재하지 않습니다. review Id : " + reviewId));
//    }
}
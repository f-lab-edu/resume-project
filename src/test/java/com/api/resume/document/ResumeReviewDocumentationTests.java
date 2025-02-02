package com.api.resume.document;

import com.api.resume.adapter.controller.ResumeReviewController;
import com.api.resume.adapter.payload.resumereivew.ResumeReviewCreateRequest;
import com.api.resume.adapter.payload.resumereivew.ResumeReviewUpdateRequest;
import com.api.resume.application.usecase.resumereview.*;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResumeReviewController.class)
@AutoConfigureRestDocs
public class ResumeReviewDocumentationTests {

    private static final String URI = "/api/v1/resumes/reviews";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResumeReviewListUseCase resumeReviewListUseCase;

    @MockBean
    private ResumeReviewDetailUseCase resumeReviewDetailUseCase;

    @MockBean
    private ResumeReviewCreateUseCase resumeReviewCreateUseCase;

    @MockBean
    private ResumeReviewUpdateUseCase resumeReviewUpdateUseCase;

    @MockBean
    private ResumeReviewDeleteUseCase resumeReviewDeleteUseCase;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllResumeReview() throws Exception {
        List<ResumeReviewListDto> mockData = List.of(
                ResumeReviewListDto.builder()
                        .reviewId(1L)
                        .companyName("Company A")
                        .title("Review Title A")
                        .projectStartDate(LocalDate.of(2023, 1, 1))
                        .projectEndDate(LocalDate.of(2023, 12, 31))
                        .keywords("java, spring-boot, mysql, 개선작업")
                        .build(),
                ResumeReviewListDto.builder()
                        .reviewId(2L)
                        .companyName("Company B")
                        .title("Review Title B")
                        .projectStartDate(LocalDate.of(2022, 6, 1))
                        .projectEndDate(LocalDate.of(2022, 12, 31))
                        .keywords("java, spring-boot, mysql, 개선작업")
                        .build()
        );

        Mockito.when(resumeReviewListUseCase.getAllResumeReviewList(Mockito.any(), Mockito.eq("DESC")))
                .thenReturn(mockData);

        mockMvc.perform(get("/api/v1/resumes/reviews")
                        .queryParam("direction", "DESC")
                        .queryParam("title", "")
                        .queryParam("companyName", "")
                        .queryParam("keyword", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-all-resume-reviews",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        queryParameters(
                                parameterWithName("direction").description("정렬 방향 (asc or desc)"),
                                parameterWithName("title").description("프로젝트명"),
                                parameterWithName("companyName").description("회사명"),
                                parameterWithName("keyword").description("프로젝트 키워드")
                        ),
                        responseFields(
                                fieldWithPath("[].reviewId").description("이력서 리뷰 ID"),
                                fieldWithPath("[].companyName").description("회사 이름"),
                                fieldWithPath("[].title").description("리뷰 제목"),
                                fieldWithPath("[].projectStartDate").description("프로젝트 시작 날짜"),
                                fieldWithPath("[].projectEndDate").description("프로젝트 종료 날짜"),
                                fieldWithPath("[].keywords").description("리뷰와 관련된 키워드 목록")
                        )
                ));
    }

    @Test
    void getResumeReviewById() throws Exception {
        ResumeReviewDetailDto response = ResumeReviewDetailDto.builder()
                .reviewId(1L)
                .companyName("youlala")
                .title("a project")
                .projectStartDate(LocalDate.now())
                .projectEndDate(LocalDate.now())
                .keywords("개선, java, spring-boot, jpa")
                .situation("test입니다~~")
                .task("what!")
                .actionsTaken("action!!!")
                .result("blah blah~~~ ")
                .build();

        Mockito.when(resumeReviewDetailUseCase.getResumeReview(1L))
                .thenReturn(response);

        mockMvc.perform(get(URI + "/{reviewId}", 1)
                        .contentType(APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        document(
                                "get-resume-review-detail",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("reviewId").description("프로젝트 회고 식별자")
                                ),
                                responseFields(
                                        fieldWithPath("reviewId").description("프로젝트 회고 식별자"),
                                        fieldWithPath("title").description("프로젝트명"),
                                        fieldWithPath("companyName").description("회사명"),
                                        fieldWithPath("projectStartDate").description("프로젝트 시작일"),
                                        fieldWithPath("projectEndDate").description("프로젝트 종료일"),
                                        fieldWithPath("keywords").description("프로젝트 키워드"),
                                        fieldWithPath("situation").description("상황/목표"),
                                        fieldWithPath("task").description("과제"),
                                        fieldWithPath("actionsTaken").description("행동"),
                                        fieldWithPath("result").description("결과")
                                )
                        )
                )
                .andReturn();
    }

    @Test
    void createResumeReview() throws Exception {
        ResumeReviewCreateRequest request =
                ResumeReviewCreateRequest.builder()
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

        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(document("resume-review-create",
                        preprocessRequest(prettyPrint()),
                        requestFields(
                                fieldWithPath("title").description("프로젝트 제목"),
                                fieldWithPath("companyName").description("회사명"),
                                fieldWithPath("projectStartDate").description("프로젝트 시작일"),
                                fieldWithPath("projectEndDate").description("프로젝트 종료일"),
                                fieldWithPath("keywords").description("키워드 리스트"),
                                fieldWithPath("situation").description("상황 설명"),
                                fieldWithPath("task").description("과제 설명"),
                                fieldWithPath("actionsTaken").description("수행한 행동"),
                                fieldWithPath("result").description("결과 설명")
                        )
                ));
    }

    @Test
    void updateResumeReview() throws Exception {
        ResumeReviewUpdateRequest update =
                ResumeReviewUpdateRequest.builder()
                        .userId(1L)
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

        mockMvc.perform(put(URI + "/{reviewId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andDo(document("resume-review-update",
                        preprocessRequest(prettyPrint()),
                        pathParameters(
                                parameterWithName("reviewId").description("프로젝트 회고 식별자")
                        ),
                        requestFields(
                                fieldWithPath("userId").description("사용자 id"),
                                fieldWithPath("title").description("프로젝트 제목"),
                                fieldWithPath("companyName").description("회사명"),
                                fieldWithPath("projectStartDate").description("프로젝트 시작일"),
                                fieldWithPath("projectEndDate").description("프로젝트 종료일"),
                                fieldWithPath("keywords").description("키워드 리스트"),
                                fieldWithPath("situation").description("상황 설명"),
                                fieldWithPath("task").description("과제 설명"),
                                fieldWithPath("actionsTaken").description("수행한 행동"),
                                fieldWithPath("result").description("결과 설명")
                        )
                ));
    }

    @Test
    void deleteResumeReview() throws Exception {
        mockMvc.perform(delete(URI + "/{reviewId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(document("resume-review-delete",
                        preprocessRequest(prettyPrint()),
                        pathParameters(
                                parameterWithName("reviewId").description("리뷰 식별자")
                        )
                ));
    }

}

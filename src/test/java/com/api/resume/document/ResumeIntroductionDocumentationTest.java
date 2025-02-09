package com.api.resume.document;

import com.api.resume.adapter.controller.ResumeIntroductionController;
import com.api.resume.adapter.payload.resumeintroduction.ResumeIntroductionCreateRequest;
import com.api.resume.adapter.payload.resumeintroduction.ResumeIntroductionUpdateRequest;
import com.api.resume.application.resumeintroduction.ResumeIntroductionService;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResumeIntroductionController.class)
@AutoConfigureRestDocs
class ResumeIntroductionDocumentationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ResumeIntroductionService resumeIntroductionService;

    @Test
    void documentGetAllResumeIntroduction() throws Exception {
        // given
        ResumeIntroductionListDto dto = ResumeIntroductionListDto.builder()
                .resumeIntroductionId(1L)
                .title("Test Title")
                .content("Test Content")
                .build();
        given(resumeIntroductionService.getAll(any()))
                .willReturn(List.of(dto));

        // when & then
        mockMvc.perform(get("/api/v1/resume-introduction/"))
                .andExpect(status().isOk())
                .andDo(document("resume-introduction-list",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].resumeIntroductionId").type(JsonFieldType.NUMBER).description("자기소개 ID"),
                                fieldWithPath("[].title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("[].content").type(JsonFieldType.STRING).description("내용")
                        )
                ));
    }

    @Test
    void documentGetResumeIntroductionDetail() throws Exception {
        // given
        long resumeIntroductionId = 1L;
        ResumeIntroductionDetailDto dto = ResumeIntroductionDetailDto.builder()
                .resumeIntroductionId(resumeIntroductionId)
                .title("Test Title")
                .content("Test Content")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        given(resumeIntroductionService.getResumeIntroduction(resumeIntroductionId))
                .willReturn(dto);

        // when & then
        mockMvc.perform(get("/api/v1/resume-introduction/{resumeIntroductionId}", resumeIntroductionId))
                .andExpect(status().isOk())
                .andDo(document("resume-introduction-detail",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("resumeIntroductionId").description("자기소개 ID")
                        ),
                        responseFields(
                                fieldWithPath("resumeIntroductionId").type(JsonFieldType.NUMBER).description("자기소개 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("등록일시"),
                                fieldWithPath("updatedAt").type(JsonFieldType.STRING).description("수정일시")
                        )
                ));
    }

    @Test
    void documentCreateResumeIntroduction() throws Exception {
        // given
        ResumeIntroductionCreateRequest request = new ResumeIntroductionCreateRequest("Test Title", "Test Content");
        doNothing().when(resumeIntroductionService).create(any(ResumeIntroductionCreateCommand.class));

        // when & then
        mockMvc.perform(post("/api/v1/resume-introduction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(document("resume-introduction-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
                        )
                ));
    }

    @Test
    void documentUpdateResumeIntroduction() throws Exception {
        // given
        long resumeIntroductionId = 1L;
        ResumeIntroductionUpdateRequest request = new ResumeIntroductionUpdateRequest(1L, "update title", "update content");
        doNothing().when(resumeIntroductionService).update(any(ResumeIntroductionUpdateCommand.class));

        // when & then
        mockMvc.perform(put("/api/v1/resume-introduction/{resumeIntroductionId}", resumeIntroductionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent())
                .andDo(document("resume-introduction-update",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("resumeIntroductionId").description("자기소개 ID")
                        ),
                        requestFields(
                                fieldWithPath("resumeIntroductionId").type(JsonFieldType.NUMBER).description("자기소개 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
                        )
                ));
    }

    @Test
    void documentDeleteResumeIntroduction() throws Exception {
        // given
        long resumeIntroductionId = 1L;
        doNothing().when(resumeIntroductionService).delete(resumeIntroductionId);

        // when & then
        mockMvc.perform(delete("/api/v1/resume-introduction/{resumeIntroductionId}", resumeIntroductionId))
                .andExpect(status().isNoContent())
                .andDo(document("resume-introduction-delete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("resumeIntroductionId").description("자기소개 ID")
                        )
                ));
    }
} 
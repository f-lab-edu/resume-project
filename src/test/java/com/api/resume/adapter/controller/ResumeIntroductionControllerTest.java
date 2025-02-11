package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.resumeintroduction.ResumeIntroductionCreateRequest;
import com.api.resume.adapter.payload.resumeintroduction.ResumeIntroductionUpdateRequest;
import com.api.resume.application.resumeintroduction.ResumeIntroductionUseCase;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResumeIntroductionController.class)
class ResumeIntroductionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ResumeIntroductionUseCase resumeIntroductionUseCase;

    @Test
    @DisplayName("자기소개 목록을 조회한다")
    void getAllResumeIntroductionList() throws Exception {
        // given
        ResumeIntroductionListDto dto = ResumeIntroductionListDto.builder()
                .resumeIntroductionId(1L)
                .title("Test Title")
                .content("Test Content")
                .build();
        given(resumeIntroductionUseCase.getAll(any()))
                .willReturn(List.of(dto));

        // when & then
        mockMvc.perform(get("/api/v1/resume-introduction/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].resumeIntroductionId").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test Title"))
                .andExpect(jsonPath("$[0].content").value("Test Content"));
    }

    @Test
    @DisplayName("자기소개 상세 정보를 조회한다")
    void getResumeIntroductionDetail() throws Exception {
        // given
        long resumeIntroductionId = 1L;
        ResumeIntroductionDetailDto dto = ResumeIntroductionDetailDto.builder()
                .resumeIntroductionId(resumeIntroductionId)
                .title("Test Title")
                .content("Test Content")
                .build();
        given(resumeIntroductionUseCase.getResumeIntroduction(resumeIntroductionId))
                .willReturn(dto);

        // when & then
        mockMvc.perform(get("/api/v1/resume-introduction/{resumeIntroductionId}", resumeIntroductionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resumeIntroductionId").value(resumeIntroductionId))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"));
    }

    @Test
    @DisplayName("자기소개를 생성한다")
    void createResumeIntroduction() throws Exception {
        // given
        ResumeIntroductionCreateRequest request = new ResumeIntroductionCreateRequest("Test Title", "Test Content");
        doNothing().when(resumeIntroductionUseCase).create(any(ResumeIntroductionCreateCommand.class));

        // when & then
        mockMvc.perform(post("/api/v1/resume-introduction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("자기소개를 수정한다")
    void updateResumeIntroduction() throws Exception {
        // given
        long resumeIntroductionId = 1L;
        ResumeIntroductionUpdateRequest request = new ResumeIntroductionUpdateRequest(1L, "update title", "update content");
        doNothing().when(resumeIntroductionUseCase).update(any(ResumeIntroductionUpdateCommand.class));

        // when & then
        mockMvc.perform(put("/api/v1/resume-introduction/{resumeIntroductionId}", resumeIntroductionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("자기소개를 삭제한다")
    void deleteResumeIntroduction() throws Exception {
        // given
        long resumeIntroductionId = 1L;
        doNothing().when(resumeIntroductionUseCase).delete(resumeIntroductionId);

        // when & then
        mockMvc.perform(delete("/api/v1/resume-introduction/{resumeIntroductionId}", resumeIntroductionId))
                .andExpect(status().isNoContent());
    }
} 
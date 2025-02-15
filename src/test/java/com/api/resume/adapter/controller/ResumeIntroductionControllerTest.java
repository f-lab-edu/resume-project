package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.resumeintroduction.*;
import com.api.resume.application.resumeintroduction.ResumeIntroductionUseCase;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResumeIntroductionController.class)
@ExtendWith(MockitoExtension.class)
class ResumeIntroductionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResumeIntroductionUseCase resumeIntroductionUseCase;

    @Autowired
    private ObjectMapper objectMapper;

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

        // When & Then
        mockMvc.perform(get("/api/v1/resume-introduction/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].resumeIntroductionId").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test Title"))
                .andExpect(jsonPath("$[0].content").value("Test Content"));
    }

    @Test
    @DisplayName("자기소개 상세 정보를 조회한다")
    void getResumeIntroduction() throws Exception {
        // Given
        ResumeIntroductionDetailDto resumeIntroductionDetailDto = ResumeIntroductionDetailDto.builder()
                .resumeIntroductionId(1L)
                .title("detail title")
                .content("detail content")
                .build();
        ResumeIntroductionDetailResponse response = ResumeIntroductionDetailResponse.from(resumeIntroductionDetailDto);
        given(resumeIntroductionUseCase.getResumeIntroduction(1L)).willReturn(resumeIntroductionDetailDto);

        // When & Then
        mockMvc.perform(get("/api/v1/resume-introduction/{resumeIntroductionId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("detail title"))
                .andExpect(jsonPath("$.content").value("detail content"));
    }

    @Test
    @DisplayName("자기소개 생성한다")
    void createResumeIntroduction() throws Exception {
        // Given
        long resumeIntroductionId = 1L;

        ResumeIntroductionCreateRequest request = new ResumeIntroductionCreateRequest("New Title", "New Content");
        ResumeIntroductionCreateResponse response = ResumeIntroductionCreateResponse.from(resumeIntroductionId);
        given(resumeIntroductionUseCase.create(any(ResumeIntroductionCreateCommand.class))).willReturn(response.resumeIntroductionId());

        // When & Then
        mockMvc.perform(post("/api/v1/resume-introduction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.resumeIntroductionId").value(1L));
    }

    @Test
    @DisplayName("자기소개 수정한다")
    void updateResumeIntroduction() throws Exception {
        long resumeIntroductionId = 1L;

        // Given
        ResumeIntroductionUpdateRequest request = new ResumeIntroductionUpdateRequest(resumeIntroductionId, "Updated Title", "Updated Content");
        ResumeIntroductionUpdateResponse response = ResumeIntroductionUpdateResponse.from(resumeIntroductionId);

        given(resumeIntroductionUseCase.update(any(ResumeIntroductionUpdateCommand.class))).willReturn(response.resumeIntroductionId());

        // When & Then
        mockMvc.perform(put("/api/v1/resume-introduction/{resumeIntroductionId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resumeIntroductionId")
                        .value(1L));
    }

    @Test
    @DisplayName("자기소개 삭제한다")
    void deleteResumeIntroduction() throws Exception {
        long resumeIntroductionId = 1L;

        // Given
        given(resumeIntroductionUseCase.delete(resumeIntroductionId)).willReturn(resumeIntroductionId);

        // When & Then
        mockMvc.perform(delete("/api/v1/resume-introduction/{resumeIntroductionId}", resumeIntroductionId))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
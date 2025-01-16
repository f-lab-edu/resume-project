package com.api.resume.application.service.resumeintroduction;

import com.api.resume.adapter.persistence.resumeIntroduction.ResumeIntroductionAdapter;
import com.api.resume.application.service.resumeintroduction.query.ResumeIntroductionListQuery;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;
import com.api.resume.domain.entity.ResumeIntroduction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ResumeIntroductionQueryServiceTest {

    @InjectMocks
    private ResumeIntroductionQueryService resumeIntroductionQueryService;

    @Mock
    private ResumeIntroductionAdapter resumeIntroductionAdapter;

    @Test
    @DisplayName("자기소개 목록을 조회한다")
    void getAll() {
        // given
        ResumeIntroductionListQuery query = new ResumeIntroductionListQuery();
        ResumeIntroduction resumeIntroduction = ResumeIntroduction.builder()
                .resumeIntroductionId(1L)
                .title("Test Title")
                .content("Test Content")
                .build();

        given(resumeIntroductionAdapter.getAllResumeIntroduction())
                .willReturn(List.of(resumeIntroduction));

        // when
        List<ResumeIntroductionListDto> result = resumeIntroductionQueryService.getAll(query);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getResumeIntroductionId()).isEqualTo(1L);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Title");
        assertThat(result.get(0).getContent()).isEqualTo("Test Content");
    }

    @Test
    @DisplayName("자기소개 상세 정보를 조회한다")
    void getResumeIntroduction() {
        // given
        long resumeIntroductionId = 1L;
        ResumeIntroduction resumeIntroduction = ResumeIntroduction.builder()
                .resumeIntroductionId(resumeIntroductionId)
                .title("Test Title")
                .content("Test Content")
                .build();

        given(resumeIntroductionAdapter.getResumeIntroduction(resumeIntroductionId))
                .willReturn(resumeIntroduction);

        // when
        ResumeIntroductionDetailDto result = resumeIntroductionQueryService.getResumeIntroduction(resumeIntroductionId);

        // then
        assertThat(result.getResumeIntroductionId()).isEqualTo(resumeIntroductionId);
        assertThat(result.getTitle()).isEqualTo("Test Title");
        assertThat(result.getContent()).isEqualTo("Test Content");
    }
} 
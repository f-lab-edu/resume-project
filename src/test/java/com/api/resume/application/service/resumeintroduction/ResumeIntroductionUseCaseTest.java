package com.api.resume.application.service.resumeintroduction;

import com.api.resume.adapter.persistence.resumeIntroduction.ResumeIntroductionAdapter;
import com.api.resume.application.resumeintroduction.ResumeIntroductionService;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.application.resumeintroduction.query.ResumeIntroductionListQuery;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ResumeIntroductionUseCaseTest {

    @InjectMocks
    ResumeIntroductionService domainResumeIntroductionService;

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
        List<ResumeIntroductionListDto> result = domainResumeIntroductionService.getAll(query);

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
        ResumeIntroductionDetailDto result = domainResumeIntroductionService.getResumeIntroduction(resumeIntroductionId);

        // then
        assertThat(result.getResumeIntroductionId()).isEqualTo(resumeIntroductionId);
        assertThat(result.getTitle()).isEqualTo("Test Title");
        assertThat(result.getContent()).isEqualTo("Test Content");
    }

    @Test
    @DisplayName("자기소개를 생성한다")
    void create() {
        // given
        ResumeIntroductionCreateCommand command = ResumeIntroductionCreateCommand.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        // when
        domainResumeIntroductionService.create(command);

        // then
        verify(resumeIntroductionAdapter).save(any(ResumeIntroduction.class));
    }

    @Test
    @DisplayName("자기소개를 수정한다")
    void update() {
        // given
        long resumeIntroductionId = 1L;
        ResumeIntroductionUpdateCommand command = ResumeIntroductionUpdateCommand.builder()
                .resumeIntroductionId(resumeIntroductionId)
                .title("update title")
                .content("update content")
                .build();

        ResumeIntroduction resumeIntroduction = ResumeIntroduction.builder()
                .resumeIntroductionId(resumeIntroductionId)
                .title("title")
                .content("content")
                .build();

        given(resumeIntroductionAdapter.getResumeIntroduction(resumeIntroductionId))
                .willReturn(resumeIntroduction);

        // when
        domainResumeIntroductionService.update(command);

        // then
        verify(resumeIntroductionAdapter).getResumeIntroduction(resumeIntroductionId);
    }

    @Test
    @DisplayName("자기소개를 삭제한다")
    void delete() {
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
        domainResumeIntroductionService.delete(resumeIntroductionId);

        // then
        verify(resumeIntroductionAdapter).getResumeIntroduction(resumeIntroductionId);
        verify(resumeIntroductionAdapter).delete(resumeIntroduction);
    }
}

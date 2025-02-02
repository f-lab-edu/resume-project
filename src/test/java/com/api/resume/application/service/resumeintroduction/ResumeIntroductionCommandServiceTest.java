package com.api.resume.application.service.resumeintroduction;

import com.api.resume.adapter.persistence.resumeIntroduction.ResumeIntroductionAdapter;
import com.api.resume.application.service.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.service.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.domain.entity.ResumeIntroduction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ResumeIntroductionCommandServiceTest {

    @InjectMocks
    private ResumeIntroductionCommandService resumeIntroductionCommandService;

    @Mock
    private ResumeIntroductionAdapter resumeIntroductionAdapter;

    @Test
    @DisplayName("자기소개를 생성한다")
    void create() {
        // given
        ResumeIntroductionCreateCommand command = ResumeIntroductionCreateCommand.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        // when
        resumeIntroductionCommandService.create(command);

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
        resumeIntroductionCommandService.update(command);

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
        resumeIntroductionCommandService.delete(resumeIntroductionId);

        // then
        verify(resumeIntroductionAdapter).getResumeIntroduction(resumeIntroductionId);
        verify(resumeIntroductionAdapter).delete(resumeIntroduction);
    }
} 
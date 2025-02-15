package com.api.resume.application.service.resumeintroduction;

import com.api.resume.adapter.persistence.resumeIntroduction.ResumeIntroductionRepository;
import com.api.resume.application.resumeintroduction.ResumeIntroductionService;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.application.resumeintroduction.query.ResumeIntroductionListQuery;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;
import com.api.resume.domain.entity.ResumeIntroduction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResumeIntroductionServiceTest {

    @InjectMocks
    ResumeIntroductionService resumeIntroductionService;

    @Mock
    private ResumeIntroductionRepository resumeIntroductionRepository;

    private ResumeIntroduction resumeIntroduction;

    @BeforeEach
    void setUp() {
        resumeIntroduction = ResumeIntroduction.builder()
                .resumeIntroductionId(1L)
                .title("title")
                .content("content")
                .build();
    }

    @Test
    @DisplayName("전체 자기소개 리스트 조회")
    void getAllResumeIntroductionList() {
        // Given
        given(resumeIntroductionRepository.getAllResumeIntroduction())
                .willReturn(List.of(resumeIntroduction));

        // When
        List<ResumeIntroductionListDto> result = resumeIntroductionService.getAll(new ResumeIntroductionListQuery());

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).title()).isEqualTo("title");
    }

    @Test
    @DisplayName("자기소개 단건 조회")
    void getResumeIntroduction() {
        // Given
        given(resumeIntroductionRepository.getResumeIntroduction(1L)).willReturn(resumeIntroduction);

        // When
        ResumeIntroductionDetailDto result = resumeIntroductionService.getResumeIntroduction(1L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.title()).isEqualTo("title");
    }

    @Test
    @DisplayName("자기소개 생성")
    void createResumeIntroduction() {
        // Given
        ResumeIntroductionCreateCommand command = ResumeIntroductionCreateCommand.builder()
                .title("New Title")
                .content("New Content")
                .build();

        ResumeIntroduction savedResumeIntroduction = ResumeIntroduction.create(command);

        given(resumeIntroductionRepository.save(any(ResumeIntroduction.class)))
                .willReturn(savedResumeIntroduction);

        // When
        long resumeIntroductionId = resumeIntroductionService.create(command);

        // Then
        assertThat(resumeIntroductionId).isEqualTo(1L);
    }

    @Test
    @DisplayName("자기소개 수정")
    void updateResumeIntroduction() {
        // Given
        ResumeIntroductionUpdateCommand command = ResumeIntroductionUpdateCommand.builder()
                .resumeIntroductionId(1L)
                .title("Updated Title")
                .content("Updated Content")
                .build();

        given(resumeIntroductionRepository.getResumeIntroduction(1L)).willReturn(resumeIntroduction);

        // When
        long updatedId = resumeIntroductionService.update(command);

        // Then
        assertThat(updatedId).isEqualTo(1L);
        assertThat(resumeIntroduction.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    @DisplayName("자기소개 삭제")
    void deleteResumeIntroduction() {
        // Given
        given(resumeIntroductionRepository.getResumeIntroduction(1L)).willReturn(resumeIntroduction);
        doNothing().when(resumeIntroductionRepository).delete(resumeIntroduction);

        // When
        long deletedId = resumeIntroductionService.delete(1L);

        // Then
        assertThat(deletedId).isEqualTo(1L);
        verify(resumeIntroductionRepository, times(1)).delete(resumeIntroduction);
    }

    @Test
    @DisplayName("존재하지 않는 자기소개 조회 시 예외 발생")
    void getResumeIntroduction_NotFound() {
        // Given
        given(resumeIntroductionRepository.getResumeIntroduction(2L)).willReturn(null);

        // When & Then
        assertThatThrownBy(() -> resumeIntroductionService.getResumeIntroduction(2L))
                .isInstanceOf(NullPointerException.class);
    }
}

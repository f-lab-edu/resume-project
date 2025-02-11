package com.api.resume.domain.dto;

import com.api.resume.domain.entity.ResumeIntroduction;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
public class ResumeIntroductionListDto {

    long resumeIntroductionId;
    String title;
    String content;

    public static List<ResumeIntroductionListDto> from(final List<ResumeIntroduction> resumeIntroductions) {
        return resumeIntroductions.stream()
                .map(ResumeIntroductionListDto::fromResumeIntroductions)
                .collect(Collectors.toList());
    }

    private static ResumeIntroductionListDto fromResumeIntroductions(final ResumeIntroduction resumeIntroduction) {
        return new ResumeIntroductionListDto(
                resumeIntroduction.getResumeIntroductionId(),
                resumeIntroduction.getTitle(),
                resumeIntroduction.getContent()
        );
    }
}

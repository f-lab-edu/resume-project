package com.api.resume.adapter.payload.resumeintroduction;

import com.api.resume.domain.dto.ResumeIntroductionListDto;

import java.util.List;
import java.util.stream.Collectors;

public record ResumeIntroductionListResponse(long resumeIntroductionId,
                                             String title,
                                             String content) {

    public static List<ResumeIntroductionListResponse> from(final List<ResumeIntroductionListDto> resumeIntroductions) {
        return resumeIntroductions.stream()
                .map(ResumeIntroductionListResponse::fromDto)
                .collect(Collectors.toList());
    }

    private static ResumeIntroductionListResponse fromDto(final ResumeIntroductionListDto dto) {
        return new ResumeIntroductionListResponse(dto.resumeIntroductionId(),
                dto.title(),
                dto.content());
    }
}

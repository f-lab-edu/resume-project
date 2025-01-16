package com.api.resume.application.usecase.resumeintroduction;

import com.api.resume.application.service.resumeintroduction.query.ResumeIntroductionListQuery;
import com.api.resume.domain.dto.ResumeIntroductionListDto;

import java.util.List;

public interface ResumeIntroductionListUseCase {
    List<ResumeIntroductionListDto> getAll(ResumeIntroductionListQuery query);
}

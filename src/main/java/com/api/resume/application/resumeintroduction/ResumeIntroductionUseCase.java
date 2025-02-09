package com.api.resume.application.resumeintroduction;

import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.application.resumeintroduction.query.ResumeIntroductionListQuery;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;

import java.util.List;

public interface ResumeIntroductionUseCase {
    List<ResumeIntroductionListDto> getAll(ResumeIntroductionListQuery query);
    ResumeIntroductionDetailDto getResumeIntroduction(final long resumeIntroductionId);
    void create(ResumeIntroductionCreateCommand command);
    void update(ResumeIntroductionUpdateCommand command);
    void delete(final long resumeIntroductionId);
}

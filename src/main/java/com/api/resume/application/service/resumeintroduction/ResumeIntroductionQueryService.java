package com.api.resume.application.service.resumeintroduction;

import com.api.resume.adapter.persistence.resumeIntroduction.ResumeIntroductionAdapter;
import com.api.resume.application.service.resumeintroduction.query.ResumeIntroductionListQuery;
import com.api.resume.application.usecase.resumeintroduction.ResumeIntroductionDetailUseCase;
import com.api.resume.application.usecase.resumeintroduction.ResumeIntroductionListUseCase;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;
import com.api.resume.domain.entity.ResumeIntroduction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeIntroductionQueryService implements ResumeIntroductionListUseCase
                                                     , ResumeIntroductionDetailUseCase {

    private final ResumeIntroductionAdapter resumeIntroductionAdapter;

    @Override
    public List<ResumeIntroductionListDto> getAll(ResumeIntroductionListQuery query) {
        List<ResumeIntroduction> resumeIntroductions =
                resumeIntroductionAdapter.getAllResumeIntroduction();
        return ResumeIntroductionListDto.from(resumeIntroductions);
    }

    @Override
    public ResumeIntroductionDetailDto getResumeIntroduction(final long resumeIntroductionId) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionAdapter.getResumeIntroduction(resumeIntroductionId);
        return ResumeIntroductionDetailDto.from(resumeIntroduction);
    }
}

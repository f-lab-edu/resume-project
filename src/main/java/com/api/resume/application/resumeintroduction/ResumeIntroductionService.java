package com.api.resume.application.resumeintroduction;

import com.api.resume.adapter.persistence.resumeIntroduction.ResumeIntroductionAdapter;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.application.resumeintroduction.query.ResumeIntroductionListQuery;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;
import com.api.resume.domain.entity.ResumeIntroduction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeIntroductionService implements ResumeIntroductionUseCase {
    private final ResumeIntroductionAdapter resumeIntroductionAdapter;

    @Transactional(readOnly = true)
    @Override
    public List<ResumeIntroductionListDto> getAll(ResumeIntroductionListQuery query) {
        List<ResumeIntroduction> resumeIntroductions =
                resumeIntroductionAdapter.getAllResumeIntroduction();
        return ResumeIntroductionListDto.from(resumeIntroductions);
    }

    @Transactional(readOnly = true)
    @Override
    public ResumeIntroductionDetailDto getResumeIntroduction(final long resumeIntroductionId) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionAdapter.getResumeIntroduction(resumeIntroductionId);
        return ResumeIntroductionDetailDto.from(resumeIntroduction);
    }

    @Transactional
    @Override
    public void create(ResumeIntroductionCreateCommand command) {
        resumeIntroductionAdapter.save(ResumeIntroduction.create(command));
    }

    @Transactional
    @Override
    public void update(ResumeIntroductionUpdateCommand command) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionAdapter.getResumeIntroduction(command.getResumeIntroductionId());

        resumeIntroduction.modify(command);
    }

    @Transactional
    @Override
    public void delete(long resumeIntroductionId) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionAdapter.getResumeIntroduction(resumeIntroductionId);

        resumeIntroductionAdapter.delete(resumeIntroduction);
    }
}

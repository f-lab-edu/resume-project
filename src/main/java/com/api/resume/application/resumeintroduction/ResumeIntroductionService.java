package com.api.resume.application.resumeintroduction;

import com.api.resume.adapter.persistence.resumeIntroduction.ResumeIntroductionRepository;
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
    private final ResumeIntroductionRepository resumeIntroductionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ResumeIntroductionListDto> getAll(ResumeIntroductionListQuery query) {
        List<ResumeIntroduction> resumeIntroductions =
                resumeIntroductionRepository.getAllResumeIntroduction();
        return ResumeIntroductionListDto.from(resumeIntroductions);
    }

    @Transactional(readOnly = true)
    @Override
    public ResumeIntroductionDetailDto getResumeIntroduction(final long resumeIntroductionId) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionRepository.getResumeIntroduction(resumeIntroductionId);
        return ResumeIntroductionDetailDto.from(resumeIntroduction);
    }

    @Transactional
    @Override
    public long create(ResumeIntroductionCreateCommand command) {
        return resumeIntroductionRepository
                .save(ResumeIntroduction.create(command))
                .getResumeIntroductionId();
    }

    @Transactional
    @Override
    public long update(ResumeIntroductionUpdateCommand command) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionRepository.getResumeIntroduction(command.resumeIntroductionId());

        resumeIntroduction.modify(command);
        return resumeIntroduction.getResumeIntroductionId();
    }

    @Transactional
    @Override
    public long delete(long resumeIntroductionId) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionRepository.getResumeIntroduction(resumeIntroductionId);

        resumeIntroductionRepository.delete(resumeIntroduction);
        return resumeIntroductionId;
    }
}

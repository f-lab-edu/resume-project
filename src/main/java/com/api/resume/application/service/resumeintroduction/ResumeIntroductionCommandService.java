package com.api.resume.application.service.resumeintroduction;

import com.api.resume.adapter.persistence.resumeIntroduction.ResumeIntroductionAdapter;
import com.api.resume.application.service.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.service.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.application.usecase.resumeintroduction.*;
import com.api.resume.domain.entity.ResumeIntroduction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ResumeIntroductionCommandService implements ResumeIntroductionCreateUseCase
        , ResumeIntroductionUpdateUseCase
        , ResumeIntroductionDeleteUseCase {

    private final ResumeIntroductionAdapter resumeIntroductionAdapter;

    @Override
    public void create(ResumeIntroductionCreateCommand command) {
        resumeIntroductionAdapter.save(ResumeIntroduction.create(command));
    }

    @Override
    public void update(ResumeIntroductionUpdateCommand command) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionAdapter.getResumeIntroduction(command.getResumeIntroductionId());

        resumeIntroduction.modify(command);
    }

    @Override
    public void delete(long resumeIntroductionId) {
        ResumeIntroduction resumeIntroduction =
                resumeIntroductionAdapter.getResumeIntroduction(resumeIntroductionId);

        resumeIntroductionAdapter.delete(resumeIntroduction);
    }

}

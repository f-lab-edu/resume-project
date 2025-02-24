package com.api.resume.adapter.persistence.resumeIntroduction;

import com.api.resume.domain.entity.ResumeIntroduction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeIntroductionRepository {

    private final ResumeIntroductionJpaRepository resumeIntroductionJpaRepository;

    public List<ResumeIntroduction> getAllResumeIntroduction() {
        return resumeIntroductionJpaRepository.findAll();
    }

    public ResumeIntroduction getResumeIntroduction(final long resumeIntroductionId) {
        return resumeIntroductionJpaRepository.findById(resumeIntroductionId)
                .orElseThrow(() -> new IllegalArgumentException("자기소개가 존재하지 않습니다. review Id : " + resumeIntroductionId));
    }

    public ResumeIntroduction save(final ResumeIntroduction resumeIntroduction) {
        return resumeIntroductionJpaRepository.save(resumeIntroduction);
    }

    public void delete(final ResumeIntroduction resumeIntroduction) {
        resumeIntroductionJpaRepository.delete(resumeIntroduction);
    }
}

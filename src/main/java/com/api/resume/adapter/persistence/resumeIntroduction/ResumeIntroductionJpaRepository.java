package com.api.resume.adapter.persistence.resumeIntroduction;

import com.api.resume.domain.entity.ResumeIntroduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeIntroductionJpaRepository extends JpaRepository<ResumeIntroduction, Long> {
}

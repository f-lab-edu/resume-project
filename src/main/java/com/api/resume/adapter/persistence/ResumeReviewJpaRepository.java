package com.api.resume.adapter.persistence;

import com.api.resume.domain.entity.ResumeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeReviewJpaRepository extends JpaRepository<ResumeReview, Long>, JpaSpecificationExecutor<ResumeReview> {

}

package com.api.resume.integ;

import com.api.resume.adapter.persistence.resumereview.ResumeReviewRepository;
import com.api.resume.adapter.persistence.resumereview.ResumeReviewJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class ResumeReviewIntegrationTest {

    @Autowired
    private ResumeReviewJpaRepository resumeReviewJpaRepository;

    @Autowired
    private ResumeReviewRepository resumeReviewRepository;


}
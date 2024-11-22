package com.api.resume.integ;

import com.api.resume.adapter.persistence.ResumeReviewAdapter;
import com.api.resume.adapter.persistence.ResumeReviewJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class ResumeReviewIntegrationTest {

    @Autowired
    private ResumeReviewJpaRepository resumeReviewJpaRepository;

    @Autowired
    private ResumeReviewAdapter resumeReviewAdapter;


}
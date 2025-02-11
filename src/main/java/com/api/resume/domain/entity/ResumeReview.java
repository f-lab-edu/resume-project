package com.api.resume.domain.entity;

import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "resume_review", indexes = {
        @Index(name = "IDX_resume_review_company_name", columnList = "company_name"),
        @Index(name = "IDX_resume_review_keywords", columnList = "keywords"),
        @Index(name = "IDX_resume_review_title", columnList = "title"),
        @Index(name = "IDX_resume_review_user_id_project_start_date", columnList = "user_id, project_start_date")
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ResumeReview {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Lob
    @Column(name = "situation", columnDefinition = "LONGTEXT")
    private String situation;

    @Lob
    @Column(name = "task", columnDefinition = "LONGTEXT")
    private String task;

    @Lob
    @Column(name = "actions_taken", columnDefinition = "LONGTEXT")
    private String actionsTaken;

    @Lob
    @Column(name = "result", columnDefinition = "LONGTEXT")
    private String result;

    @Column(name = "keywords", columnDefinition = "TEXT")
    private String keywords;

    @Column(name = "project_start_date")
    private LocalDate projectStartDate;

    @Column(name = "project_end_date")
    private LocalDate projectEndDate;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static ResumeReview create(ResumeReviewCreateCommand command) {

        ResumeReview review = new ResumeReview();
        review.title = command.getTitle();
        review.companyName = command.getCompanyName();
        review.situation = command.getSituation();
        review.task = command.getTask();
        review.actionsTaken = command.getActionsTaken();
        review.result = command.getResult();
        review.keywords = command.getKeywords();
        review.projectStartDate = command.getProjectStartDate();
        review.projectEndDate = command.getProjectEndDate();

        return review;
    }

    public void modify(ResumeReviewUpdateCommand command) {
        this.title = command.getTitle();
        this.companyName = command.getCompanyName();
        this.situation = command.getSituation();
        this.task = command.getTask();
        this.actionsTaken = command.getActionsTaken();
        this.result = command.getResult();
        this.keywords = command.getKeywords();
        this.projectStartDate = command.getProjectStartDate();
        this.projectEndDate = command.getProjectEndDate();
    }

}
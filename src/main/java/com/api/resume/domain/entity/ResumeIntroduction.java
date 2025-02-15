package com.api.resume.domain.entity;

import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "resume_introduction")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ResumeIntroduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_introduction_id", nullable = false, updatable = false)
    private Long resumeIntroductionId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "content")
    private String content;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static ResumeIntroduction create(ResumeIntroductionCreateCommand command) {
        ResumeIntroduction introduction = new ResumeIntroduction();
        introduction.title = command.title();
        introduction.content = command.content();
        return introduction;
    }

    public void modify(ResumeIntroductionUpdateCommand command) {
        this.title = command.title();
        this.content = command.content();
    }
}
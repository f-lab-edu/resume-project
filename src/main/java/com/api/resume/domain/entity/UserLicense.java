package com.api.resume.domain.entity;

import com.api.resume.application.user.command.UserCreateCommand;
import com.api.resume.application.user.command.UserUpdateCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_license")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class UserLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_license_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "license_name", length = 50)
    private String licenseName;

    @Column(name = "issuing_authority", length = 50)
    private String issuingAuthority;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "license_number", length = 50)
    private String licenseNumber;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static UserLicense create(final UserCreateCommand command) {
        UserLicense userLicense = new UserLicense();
        return userLicense;
    }

    public void modify(final UserUpdateCommand command) {
        this.licenseName = command.getName();
        this.issuingAuthority = command.getEmail();
        this.issueDate = command.getBirthDate();
        this.licenseNumber = command.getPhoneNumber();
    }
}

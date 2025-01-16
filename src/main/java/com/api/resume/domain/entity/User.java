package com.api.resume.domain.entity;

import com.api.resume.application.service.user.command.UserCreateCommand;
import com.api.resume.application.service.user.command.UserUpdateCommand;
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
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static User create(final UserCreateCommand command) {
        User user = new User();
        user.name = command.getName();
        user.email = command.getEmail();
        user.birthDate = command.getBirthDate();
        user.phoneNumber = command.getPhoneNumber();
        return user;
    }

    public void modify(final UserUpdateCommand command) {
        this.name = command.getName();
        this.email = command.getEmail();
        this.birthDate = command.getBirthDate();
        this.phoneNumber = command.getPhoneNumber();
    }
}



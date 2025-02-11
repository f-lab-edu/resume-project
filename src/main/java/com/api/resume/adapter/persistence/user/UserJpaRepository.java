package com.api.resume.adapter.persistence.user;

import com.api.resume.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}

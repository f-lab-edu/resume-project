package com.api.resume.adapter.persistence.userlicense;

import com.api.resume.domain.entity.UserLicense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLicenseJpaRepository extends JpaRepository<UserLicense, Long> {

    List<UserLicense> findAllByUserId(long userId);
    UserLicense findByUserIdAndId(long userId, long userLicenseId);
}

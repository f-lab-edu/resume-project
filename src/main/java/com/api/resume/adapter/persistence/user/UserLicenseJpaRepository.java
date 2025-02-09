package com.api.resume.adapter.persistence.user;

import com.api.resume.domain.entity.UserLicense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLicenseJpaRepository extends JpaRepository<UserLicense, Long> {

    public List<UserLicense> findAllByUserId(long userId);
}

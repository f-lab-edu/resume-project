package com.api.resume.adapter.persistence.userlicense;

import com.api.resume.application.userlicense.query.UserLicenseDetailQuery;
import com.api.resume.domain.entity.UserLicense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserLicenseAdapter {

    private final UserLicenseJpaRepository userLicenseJpaRepository;

    public List<UserLicense> getAllUserLicenses(final long userId) {
        return userLicenseJpaRepository.findAllByUserId(userId);
    }

    public UserLicense getUserLicense(final UserLicenseDetailQuery query) {
        return userLicenseJpaRepository.findByUserIdAndId(query.getUserId(), query.getUserLicenseId());
    }
}

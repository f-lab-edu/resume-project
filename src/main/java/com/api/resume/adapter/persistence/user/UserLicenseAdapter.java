package com.api.resume.adapter.persistence.user;

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
}

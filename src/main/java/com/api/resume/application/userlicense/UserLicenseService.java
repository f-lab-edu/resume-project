package com.api.resume.application.userlicense;

import com.api.resume.adapter.persistence.userlicense.UserLicenseRepository;
import com.api.resume.application.userlicense.query.UserLicenseDetailQuery;
import com.api.resume.domain.dto.UserLicenseDetailDto;
import com.api.resume.domain.dto.UserLicenseListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLicenseService implements UserLicenseUseCase {

    private final UserLicenseRepository userLicenseRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserLicenseListDto> getAllUserLicenses(final long userId) {
        return UserLicenseListDto.from(userLicenseRepository.getAllUserLicenses(userId));
    }

    @Override
    public UserLicenseDetailDto getUserLicense(UserLicenseDetailQuery query) {
        return UserLicenseDetailDto.from(userLicenseRepository.getUserLicense(query));
    }
}

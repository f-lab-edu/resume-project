package com.api.resume.application.userlicense;

import com.api.resume.adapter.persistence.userlicense.UserLicenseAdapter;
import com.api.resume.application.userlicense.query.UserLicenseDetailQuery;
import com.api.resume.domain.dto.UserLicenseDetailDto;
import com.api.resume.domain.dto.UserLicenseListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainUserLicenseService implements UserLicenseService {

    private final UserLicenseAdapter userLicenseAdapter;

    @Transactional(readOnly = true)
    @Override
    public List<UserLicenseListDto> getAllUserLicenses(final long userId) {
        return UserLicenseListDto.from(userLicenseAdapter.getAllUserLicenses(userId));
    }

    @Override
    public UserLicenseDetailDto getUserLicense(UserLicenseDetailQuery query) {
        return null;
    }
}

package com.api.resume.application.user;

import com.api.resume.adapter.persistence.user.UserLicenseAdapter;
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
}

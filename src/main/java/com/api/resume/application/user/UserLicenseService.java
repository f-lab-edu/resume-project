package com.api.resume.application.user;

import com.api.resume.domain.dto.UserLicenseListDto;

import java.util.List;

public interface UserLicenseService {
    List<UserLicenseListDto> getAllUserLicenses(final long userId);

}

package com.api.resume.application.userlicense;

import com.api.resume.application.userlicense.query.UserLicenseDetailQuery;
import com.api.resume.domain.dto.UserLicenseDetailDto;
import com.api.resume.domain.dto.UserLicenseListDto;

import java.util.List;

public interface UserLicenseUseCase {
    List<UserLicenseListDto> getAllUserLicenses(final long userId);
    UserLicenseDetailDto getUserLicense(UserLicenseDetailQuery query);
}

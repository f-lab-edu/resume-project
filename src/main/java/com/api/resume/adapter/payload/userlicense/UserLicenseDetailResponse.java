package com.api.resume.adapter.payload.userlicense;

import com.api.resume.domain.dto.UserLicenseDetailDto;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserLicenseDetailResponse {
    Long userLicenseId;
    String licenseName;
    String issuingAuthority;
    LocalDate issueDate;
    String licenseNumber;

    public static UserLicenseDetailResponse from(final UserLicenseDetailDto userLicenseDetailDto) {
        return UserLicenseDetailResponse.builder()
                .userLicenseId(userLicenseDetailDto.getUserLicenseId())
                .licenseName(userLicenseDetailDto.getLicenseName())
                .issuingAuthority(userLicenseDetailDto.getIssuingAuthority())
                .issueDate(userLicenseDetailDto.getIssueDate())
                .licenseNumber(userLicenseDetailDto.getLicenseNumber())
                .build();
    }
}

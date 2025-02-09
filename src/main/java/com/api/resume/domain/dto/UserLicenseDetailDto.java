package com.api.resume.domain.dto;

import com.api.resume.domain.entity.UserLicense;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserLicenseDetailDto {

    Long userLicenseId;
    String licenseName;
    String issuingAuthority;
    LocalDate issueDate;
    String licenseNumber;

    public static UserLicenseDetailDto from(final UserLicense userLicense) {
        return UserLicenseDetailDto.builder()
                .userLicenseId(userLicense.getId())
                .licenseName(userLicense.getLicenseName())
                .issuingAuthority(userLicense.getIssuingAuthority())
                .issueDate(userLicense.getIssueDate())
                .licenseNumber(userLicense.getLicenseNumber())
                .build();
    }

}

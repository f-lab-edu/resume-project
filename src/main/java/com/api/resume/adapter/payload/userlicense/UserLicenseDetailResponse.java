package com.api.resume.adapter.payload.userlicense;

import com.api.resume.domain.dto.UserLicenseDetailDto;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserLicenseDetailResponse(Long userLicenseId,
                                        String licenseName,
                                        String issuingAuthority,
                                        LocalDate issueDate,
                                        String licenseNumber) {

    public static UserLicenseDetailResponse from(final UserLicenseDetailDto userLicenseDetailDto) {
        return UserLicenseDetailResponse.builder()
                .userLicenseId(userLicenseDetailDto.userLicenseId())
                .licenseName(userLicenseDetailDto.licenseName())
                .issuingAuthority(userLicenseDetailDto.issuingAuthority())
                .issueDate(userLicenseDetailDto.issueDate())
                .licenseNumber(userLicenseDetailDto.licenseNumber())
                .build();
    }
}

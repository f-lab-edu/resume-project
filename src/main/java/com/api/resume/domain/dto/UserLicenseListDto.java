package com.api.resume.domain.dto;

import com.api.resume.domain.entity.UserLicense;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record UserLicenseListDto(Long userLicenseId,
                                 String licenseName,
                                 String issuingAuthority,
                                 LocalDate issueDate,
                                 String licenseNumber) {

    public static List<UserLicenseListDto> from(final List<UserLicense> userLicenses) {
        return userLicenses.stream()
                .map(UserLicenseListDto::fromUserLicenses)
                .collect(Collectors.toList());
    }

    public static UserLicenseListDto fromUserLicenses(final UserLicense userLicense) {
        return UserLicenseListDto.builder()
                .userLicenseId(userLicense.getId())
                .licenseName(userLicense.getLicenseName())
                .issuingAuthority(userLicense.getIssuingAuthority())
                .issueDate(userLicense.getIssueDate())
                .licenseNumber(userLicense.getLicenseNumber())
                .build();
    }
}

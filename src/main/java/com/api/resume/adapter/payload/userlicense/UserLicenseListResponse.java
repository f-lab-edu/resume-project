package com.api.resume.adapter.payload.userlicense;

import com.api.resume.domain.dto.UserLicenseListDto;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record UserLicenseListResponse(Long userLicenseId,
                                      String licenseName,
                                      String issuingAuthority,
                                      LocalDate issueDate,
                                      String licenseNumber) {

    public static List<UserLicenseListResponse> from(final List<UserLicenseListDto> userLicenses) {
        return userLicenses.stream()
                .map(UserLicenseListResponse::fromDto)
                .collect(Collectors.toList());
    }

    private static UserLicenseListResponse fromDto(final UserLicenseListDto dto) {
        return new UserLicenseListResponse(dto.userLicenseId(),
                dto.licenseName(),
                dto.issuingAuthority(),
                dto.issueDate(),
                dto.licenseNumber());
    }
}

package com.api.resume.adapter.payload.user;

import com.api.resume.domain.dto.UserLicenseListDto;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
public class UserLicenseListResponse {

    Long userLicenseId;
    String licenseName;
    String issuingAuthority;
    LocalDate issueDate;
    String licenseNumber;

    public static List<UserLicenseListResponse> from(final List<UserLicenseListDto> userLicenses) {
        return userLicenses.stream()
                .map(UserLicenseListResponse::fromDto)
                .collect(Collectors.toList());
    }

    private static UserLicenseListResponse fromDto(final UserLicenseListDto dto) {
        return new UserLicenseListResponse(dto.getUserLicenseId(),
                dto.getLicenseName(),
                dto.getIssuingAuthority(),
                dto.getIssueDate(),
                dto.getLicenseNumber());
    }
}

package com.api.resume.domain.dto;

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

}

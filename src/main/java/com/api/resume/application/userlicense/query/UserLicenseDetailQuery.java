package com.api.resume.application.userlicense.query;

import lombok.Value;

@Value
public class UserLicenseDetailQuery {
    long userId;
    long userLicenseId;
}

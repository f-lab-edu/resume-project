package com.api.resume.application.userlicense.query;

import lombok.Value;

@Value
public class UserLicenseDetailQuery {
    long userId;
    long userLicenseId;

    public static UserLicenseDetailQuery of(final long userId, final long userLicenseId) {
        return new UserLicenseDetailQuery(userId, userLicenseId);
    }
}

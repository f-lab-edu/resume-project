package com.api.resume.adapter.payload.user;

public record UserUpdateResponse(long userId) {

    public static UserUpdateResponse from(final long userId) {
        return new UserUpdateResponse(userId);
    }

}

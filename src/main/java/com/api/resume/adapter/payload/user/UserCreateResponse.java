package com.api.resume.adapter.payload.user;

public record UserCreateResponse(long userId) {

    public static UserCreateResponse from(final long userId) {
        return new UserCreateResponse(userId);
    }

}

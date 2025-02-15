package com.api.resume.adapter.payload.user;

import com.api.resume.domain.dto.UserDetailDto;

import java.time.LocalDate;

public record UserDetailResponse(long userId,
                                 String email,
                                 String name,
                                 String phoneNumber,
                                 LocalDate birthDate) {

    public static UserDetailResponse from(UserDetailDto userDetailDto) {
        return new UserDetailResponse(
                userDetailDto.userId(),
                userDetailDto.email(),
                userDetailDto.name(),
                userDetailDto.phoneNumber(),
                userDetailDto.birthDate()
        );
    }
}

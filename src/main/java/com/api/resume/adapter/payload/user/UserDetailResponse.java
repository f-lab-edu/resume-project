package com.api.resume.adapter.payload.user;

import com.api.resume.domain.dto.UserDetailDto;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserDetailResponse {

    long userId;
    String email;
    String name;
    String phoneNumber;
    LocalDate birthDate;

    public static UserDetailResponse from(UserDetailDto userDetailDto) {
        return new UserDetailResponse(
                userDetailDto.getUserId(),
                userDetailDto.getEmail(),
                userDetailDto.getName(),
                userDetailDto.getPhoneNumber(),
                userDetailDto.getBirthDate()
        );
    }
}

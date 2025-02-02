package com.api.resume.domain.dto;

import com.api.resume.domain.entity.User;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDetailDto {
    long userId;
    String email;
    String name;
    String phoneNumber;
    LocalDate birthDate;

    public static UserDetailDto from(User user) {
        return UserDetailDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .build();
    }
}

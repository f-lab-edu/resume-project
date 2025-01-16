package com.api.resume.adapter.payload.user;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UserUpdateRequest {
    String email;
    String name;
    String phoneNumber;
    LocalDate birthDate;
}

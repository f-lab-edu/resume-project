package com.api.resume.adapter.payload.user;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UserCreateRequest {
    String email;
    String name;
    String phoneNumber;
    LocalDate birthDate;
}

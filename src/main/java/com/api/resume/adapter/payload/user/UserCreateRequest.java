package com.api.resume.adapter.payload.user;

import java.time.LocalDate;

public record UserCreateRequest(String email, String name, String phoneNumber, LocalDate birthDate) {
}

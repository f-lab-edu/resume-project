package com.api.resume.adapter.payload.user;

import java.time.LocalDate;

public record UserUpdateRequest(String email, String name, String phoneNumber, LocalDate birthDate) {
}

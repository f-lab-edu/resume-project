package com.api.resume.application.user.command;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
public record UserCreateCommand(String email, String name, String phoneNumber, LocalDate birthDate) {
}

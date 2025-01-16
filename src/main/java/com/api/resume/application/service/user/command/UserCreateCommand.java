package com.api.resume.application.service.user.command;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserCreateCommand {
    String email;
    String name;
    String phoneNumber;
    LocalDate birthDate;
}

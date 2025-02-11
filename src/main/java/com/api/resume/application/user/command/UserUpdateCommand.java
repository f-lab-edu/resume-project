package com.api.resume.application.user.command;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserUpdateCommand {
    long userId;
    String email;
    String name;
    String phoneNumber;
    LocalDate birthDate;
}

package com.api.resume.application.usecase.user;

import com.api.resume.application.service.user.command.UserCreateCommand;

public interface UserCreateUseCase {
    void create(final UserCreateCommand command);
}

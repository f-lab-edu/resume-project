package com.api.resume.application.usecase.user;

import com.api.resume.application.service.user.command.UserUpdateCommand;

public interface UserUpdateUseCase {
    void update(final UserUpdateCommand command);
}

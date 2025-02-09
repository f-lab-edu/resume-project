package com.api.resume.application.user;

import com.api.resume.application.user.command.UserCreateCommand;
import com.api.resume.application.user.command.UserUpdateCommand;
import com.api.resume.domain.dto.UserDetailDto;

public interface UserUseCase {
    UserDetailDto getUser(final long userId);
    void create(final UserCreateCommand command);
    void update(final UserUpdateCommand command);
}

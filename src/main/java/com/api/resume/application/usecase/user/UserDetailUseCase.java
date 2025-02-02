package com.api.resume.application.usecase.user;

import com.api.resume.domain.dto.UserDetailDto;

public interface UserDetailUseCase {
    UserDetailDto getUser(final long userId);
}

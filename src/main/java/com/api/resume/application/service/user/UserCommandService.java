package com.api.resume.application.service.user;

import com.api.resume.adapter.persistence.user.UserAdapter;
import com.api.resume.application.service.user.command.UserCreateCommand;
import com.api.resume.application.service.user.command.UserUpdateCommand;
import com.api.resume.application.usecase.user.UserCreateUseCase;
import com.api.resume.application.usecase.user.UserUpdateUseCase;
import com.api.resume.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandService implements UserCreateUseCase, UserUpdateUseCase {

    private final UserAdapter userAdapter;

    @Override
    public void create(UserCreateCommand command) {
        userAdapter.save(User.create(command));
    }

    @Override
    public void update(UserUpdateCommand command) {
        User user = userAdapter.findById(command.getUserId());
        user.modify(command);
    }
}

package com.api.resume.application.user;

import com.api.resume.adapter.persistence.user.UserAdapter;
import com.api.resume.application.user.command.UserCreateCommand;
import com.api.resume.application.user.command.UserUpdateCommand;
import com.api.resume.domain.dto.UserDetailDto;
import com.api.resume.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserAdapter userAdapter;

    @Transactional(readOnly = true)
    @Override
    public UserDetailDto getUser(long userId) {
        User user = userAdapter.findById(userId);
        return UserDetailDto.from(user);
    }

    @Transactional
    @Override
    public void create(UserCreateCommand command) {
        userAdapter.save(User.create(command));
    }

    @Transactional
    @Override
    public void update(UserUpdateCommand command) {
        User user = userAdapter.findById(command.getUserId());
        user.modify(command);
    }
}
